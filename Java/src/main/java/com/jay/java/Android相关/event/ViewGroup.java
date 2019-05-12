package com.jay.java.Android相关.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Jay On 2019/5/12 15:24
 * <p>
 * Description: 模拟Android中的ViewGroup类
 */
public class ViewGroup extends View {
    //用来记录最先接收事件的那个View
    private TouchTarget firstTouchTarget;
    //存放子View的集合
    private List<View> childList = new ArrayList<>();
    //数组查找相对集合较快
    private View[] childArray = new View[0];

    private String name;

    public ViewGroup(int left, int top, int right, int bottom) {
        super(left, top, right, bottom);
    }

    @Override
    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 向ViewGroup中添加View
     */
    public void addView(View view) {
        if (view == null) {
            return;
        }
        childList.add(view);
        //用数组操作View有性能优势
        childArray = childList.toArray(new View[childList.size()]);
    }

    /**
     * 事件分发的入口方法
     */
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println("ViewGroup-->dispatchTouchEvent");
        boolean handled = false;
        boolean intercepted = onInterceptTouchEvent(ev);
        int actionMasked = ev.getActionMasked();
        //如果当前进来的事件不是CANCEL事件并且未拦截该事件，继续向下分发该事件
        if (actionMasked != MotionEvent.ACTION_CANCEL && !intercepted) {
            //如果是DOWN事件才向下分发
            if (actionMasked == MotionEvent.ACTION_DOWN) {
                final View[] childArrayCopy = childArray;
                //后添加的View在最顶层，倒序遍历所有子View，节省查找时间
                for (int i = childArrayCopy.length - 1; i >= 0; i--) {
                    View child = childArrayCopy[i];
                    //判断边界值,如果该View不包含这个事件坐标
                    if (!child.isContain(ev.getX(), ev.getY())) {
                        continue;
                    }
                    //该View包含了这个事件坐标，能接收这个事件
                    if (dispatchTransformedTouchEvent(ev, child)) {
                        //回收池机制记录消费事件的那个View
                        addTouchTarget(child);
                        break;
                    }
                }
            }
            //没有子View消费这个事件，ViewGroup自己消费这个事件
            if (firstTouchTarget == null) {
                handled = dispatchTransformedTouchEvent(ev, null);
            }
        }
        return false;
    }

    /**
     * 事件分发
     */
    private boolean dispatchTransformedTouchEvent(MotionEvent ev, View child) {
        boolean handled = false;
        if (child != null) {
            //分发给子View处理这个事件
            handled = child.dispatchTouchEvent(ev);
        } else {
            //自己消费这个事件
            super.dispatchTouchEvent(ev);
        }
        return handled;
    }

    private TouchTarget addTouchTarget(View child) {
        TouchTarget target = TouchTarget.obtain(child);
        target.next = firstTouchTarget;
        firstTouchTarget = target;
        return target;
    }

    private static final class TouchTarget {
        //当前缓存的View
        private View child;
        //链表的的首地址，回收池
        private static TouchTarget sRecycleBin;
        //记录下一个节点的索引
        private TouchTarget next;
        private static int sRecycleBinCount;
        private static Object sObtainLock = new Object();
        private static Object sRecycleLock = new Object[0];

        /**
         * 从回收池中取对象
         */
        public static TouchTarget obtain(View view) {
            TouchTarget target;
            synchronized (sObtainLock) {
                if (sRecycleBin == null) {
                    //第一次取或者取完了的时候
                    target = new TouchTarget();
                } else {
                    //取出了一个对象
                    target = sRecycleBin;
                }
                //向后移动指针
                sRecycleBin = target.next;
                //回收池的数量-1
                sRecycleBinCount--;
                //将游离出的节点置空
                target.next = null;
            }
            return target;
        }

        /**
         * 向回收池中存对象
         */
        public void recycle() {
            if (child == null) {
                throw new IllegalStateException("已经被回收过了");
            }
            synchronized (sRecycleLock) {
                //只回收32的对象
                if (sRecycleBinCount < 32) {
                    next = sRecycleBin;
                    sRecycleBin = this;
                    sRecycleBinCount++;
                }
            }
        }

    }

    /**
     * 拦截事件
     */
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return false;
    }

}
