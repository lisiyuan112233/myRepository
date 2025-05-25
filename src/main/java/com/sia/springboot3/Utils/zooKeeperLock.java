package com.sia.springboot3.Utils;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.zookeeper.CreateMode;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.LockSupport;


@SuppressWarnings("all")
public class zooKeeperLock {
    private CuratorFramework build;
    private PathChildrenCache pathChildrenCache =null;
    private Thread currentThread =Thread.currentThread();
    private String lockId;


    public zooKeeperLock(CuratorFramework build) {
        this.build = build;
    }

    public boolean tryLock() throws InterruptedException {
        try {
            lockId = build.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/Lock/seq-");
        } catch (Exception e) {
            System.err.println(Thread.currentThread().getName()+"获取锁失败"+e);
            return false;
        }
        if (lockId==null||"".equals(lockId)){
            return false;
        }
        while (true) {
            List<String> Locks;
            try {
                Locks = build.getChildren().forPath("/Lock");
            } catch (Exception e) {
                System.err.println(Thread.currentThread().getName() + "获取锁失败" + e);
                try {
                    build.delete().forPath(lockId);
                } catch (Exception ex) {
                    return false;
                }
                return false;
            }
            if (Locks == null || Locks.isEmpty()) {
                return false;
            }
            Locks.sort((a,b)->{
                Integer a1= Integer.valueOf(a.substring(a.lastIndexOf("-")+1));
                Integer b1= Integer.valueOf(b.substring(b.lastIndexOf("-")+1));
                return a1.compareTo(b1);
            });
            if (Locks.get(0).equals(lockId.substring(lockId.lastIndexOf("/")+1))) {
                return true;
            }
                int i = Locks.indexOf(lockId.substring(lockId.lastIndexOf("/")+1));
                String s = Locks.get(i - 1);
                String initialNode = s;
                pathChildrenCache = new PathChildrenCache(build, "/Lock", true);
                try {
                    pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
                } catch (Exception e) {
                    System.err.println(Thread.currentThread().getName() + "获取锁失败" + e);
                    try {
                        build.delete().forPath(lockId);
                    } catch (Exception ex) {
                        return false;
                    }
                    return false;
                }
                pathChildrenCache.getListenable().addListener((client1, event) -> {
                    if (event.getType() == PathChildrenCacheEvent.Type.CHILD_REMOVED) {
                        if (event.getData().getPath().equals("/Lock/" + initialNode)) {
                        LockSupport.unpark(currentThread);
                    }
                }});
                    try {
                        for (int ii=0;ii<5;ii++){
                            if (!(build.checkExists().forPath("/Lock/"+initialNode) != null)) {
                                LockSupport.unpark(currentThread);
                                break;
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    LockSupport.park();
        }
    }
    public boolean unLock() throws IOException {
        if (pathChildrenCache!=null){
            pathChildrenCache.close();
        }

        try {
            build.delete().forPath(lockId);
        } catch (Exception e) {
            System.err.println(Thread.currentThread().getName()+"释放锁失败"+e);
            return false;
        }
        System.out.println(12);
        return true;
    }
}
//import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
//import java.util.Collections;
//
//public class zooKeeperLock {
//    private final CuratorFramework client;
//    private final String lockPath="/Lock";
//    private PathChildrenCache cache;
//    private String currentLockPath;
//    private Thread currentHolder;
//
//    public zooKeeperLock(CuratorFramework client) {
//        this.client = client;
//    }
//
//    public boolean tryLock() {
//        if (currentHolder == Thread.currentThread()) {
//            return true; // 可重入
//        }
//
//        try {
//            // 创建临时顺序节点
//            currentLockPath = client.create()
//                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
//                    .forPath(lockPath + "/lock-");
//
//            // 获取所有子节点并排序
//            List<String> children = client.getChildren().forPath(lockPath);
//            Collections.sort(children);
//
//            // 检查是否为第一个节点
//            String currentNode = currentLockPath.substring(currentLockPath.lastIndexOf('/') + 1);
//            if (children.get(0).equals(currentNode)) {
//                currentHolder = Thread.currentThread();
//                return true;
//            }
//
//            // 监听前一个节点
//            String previousNode = children.get(children.indexOf(currentNode) - 1);
//            cache = new PathChildrenCache(client, lockPath, true);
//            cache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
//
//            // 使用 CountDownLatch 阻塞等待
//            java.util.concurrent.CountDownLatch latch = new java.util.concurrent.CountDownLatch(1);
//            PathChildrenCacheListener listener = (client, event) -> {
//                if (event.getType() == PathChildrenCacheEvent.Type.CHILD_REMOVED) {
//                    String removedNode = event.getData().getPath().substring(event.getData().getPath().lastIndexOf('/') + 1);
//                    if (removedNode.equals(previousNode)) {
//                        latch.countDown();
//                    }
//                }
//            };
//            cache.getListenable().addListener(listener);
//
//            // 检查前一个节点是否已删除（可能在监听前就已释放）
//            if (!isNodeExists(lockPath + "/" + previousNode)) {
//                latch.countDown();
//            }
//
//            // 等待前一个节点释放
//            latch.await();
//            currentHolder = Thread.currentThread();
//            return true;
//        } catch (Exception e) {
//            releaseResources();
//            return false;
//        }
//    }
//
//    public void unLock() {
//        if (currentHolder != Thread.currentThread()) {
//            throw new IllegalMonitorStateException("当前线程未持有锁");
//        }
//
//        try {
//            if (currentLockPath != null && isNodeExists(currentLockPath)) {
//                client.delete().guaranteed().forPath(currentLockPath);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("释放锁失败", e);
//        } finally {
//            releaseResources();
//            currentHolder = null;
//            currentLockPath = null;
//        }
//    }
//
//    private boolean isNodeExists(String path) {
//        try {
//            return client.checkExists().forPath(path) != null;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    private void releaseResources() {
//        if (cache != null) {
//            try {
//                cache.close();
//            } catch (Exception e) {
//                // 记录日志
//            }
//            cache = null;
//        }
//    }
//}





















