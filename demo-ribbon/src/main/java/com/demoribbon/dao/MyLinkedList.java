package com.demoribbon.dao;

public class MyLinkedList {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    ListNode head;
    ListNode last;
    int length;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        this.length = 0;
        this.head = null;
        this.last = null;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index >= this.length) {
            return -1;
        }
        if (index == this.length - 1) {
            return last.val;
        }
        ListNode cur = this.head;
        int i = index;
        while (i != 0) {
            i--;
            cur = cur.next;
        }
        return cur.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        ListNode pre = new ListNode(val);
        if (this.length == 0) {
            this.head = pre;
            this.last = pre;
        } else if (length == 1) {
            pre.next = this.head;
            this.head = pre;
            this.last = this.head.next;
        } else {
            pre.next = this.head.next;
            this.head = pre;
        }
        this.length++;

    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        ListNode last = new ListNode(val);
        if (this.length == 0) {
            this.head = last;
            this.last = last;
        } else {
            this.last.next = last;
            this.last = last;
        }
        this.length++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > this.length - 1) {
            return;
        }
        if (index == 0) {
            this.addAtHead(val);
        }
        if (index == this.length - 1) {
            this.addAtTail(val);
        }
        ListNode node = new ListNode(val);
        int i = 0;
        ListNode cur = this.head;
        while (cur != null) {
            if (i == index) {
                ListNode tmp = cur.next;
                cur.next = node;
                node.next = tmp;
                return;
            }
            i++;
        }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index > this.length - 1) {
            return;
        }
        if (index == 0) {
            ListNode next = this.head.next;
            this.head.next = null;
            this.head = next;
            return;
        }
        int i = 0;
        ListNode cur = this.head;
        while (cur != null) {
            if (i == index - 1) {
                ListNode next = cur.next;
                System.out.println(next.val);
                cur.next = next.next;
                next.next = null;
                this.length--;
                return;
            }
            i++;
        }

    }

    static int res = 0;

    public static int rob(int[] nums) {
        helper(nums, 0, 0);
        return res;
    }

    private static void helper(int[] nums, int pos, int count) {
        if (pos >= nums.length - 2 ) {
            res = Math.max(res, count);
            return;
        }
        for (int i = pos + 2; i < nums.length; i++) {
            helper(nums, i, count + nums[i]);
        }
    }

    public static void main(String[] args) {
//        MyLinkedList m = new MyLinkedList();
//        m.addAtHead(7);
//        m.addAtTail(7);
//        m.addAtHead(9);
//        m.addAtTail(8);
//        m.addAtHead(6);
//        m.addAtHead(0);
//        m.get(5);
//        m.addAtHead(0);
//        m.get(2);
//        m.get(5);
//        m.addAtTail(4);
        int[] nums = {1,2,3,4};
        rob(nums);
    }
}
