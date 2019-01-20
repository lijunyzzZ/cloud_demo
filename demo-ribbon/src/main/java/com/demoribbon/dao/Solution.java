package com.demoribbon.dao;

import java.util.*;

public class Solution {
    String beginWord, endWord;
    Set<String> words, start, end;
    Map<String, List<String>> map;
    List<List<String>> res;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return res;
        }
        //存储图 节点及其对应关系
        Map<String, List<String>> map = getMap(beginWord, endWord, dict);
        List<String> path = new ArrayList<String>();
        path.add(beginWord);
        findPath(beginWord, endWord, res, path, map);
        return res;
    }

    public Map<String, List<String>> getMap(String beginWord, String endWord, Set<String> dict) {
        Map<String, List<String>> map = new HashMap<>();
        Set<String> start = new HashSet<>();
        start.add(beginWord);
        Set<String> end = new HashSet<>();
        end.add(endWord);
        Set<String> visited = new HashSet<>();
        boolean found = false;
        boolean isback = false;
        while (!start.isEmpty() && !found) {
            //如果start的长度大于end 的长度则交换。
            if (start.size() > end.size()) {
                Set<String> tmp = start;
                start = end;
                end = tmp;
                isback = !isback;
            }
            //set用于搜集start之后的节点。
            Set<String> set = new HashSet<>();
            for (String cur : start) {
                visited.add(cur);
                for (String next : getNext(cur, dict)) {
                    if (visited.contains(cur) || start.contains(cur)) {
                        continue;
                    }
                    if (end.contains(cur)) found = true;
                    set.add(next);
                    String parent = isback ? next : cur;
                    String child = isback ? cur : next;
                    if (!map.containsKey(parent)) {
                        map.put(parent, new ArrayList<String>());
                    }
                    map.get(parent).add(child);
                }

            }
            start = set;

        }
        return map;

    }

    public List<String> getNext(String cur, Set<String> dict) {
        List<String> res = new ArrayList<>();
        char[] chars = cur.toCharArray();
        for (int i = 0, len = cur.length(); i < len; i++) {
            char old = chars[i];
            for (char j = 'a'; j <= 'z'; j++) {
                if (j == old) {
                    continue;
                }
                String word = new String(chars);
                if (dict.contains(word)) {
                    res.add(word);
                }
            }
            chars[i] = old;
        }
        return res;
    }

    public void findPath(String start, String end, List<List<String>> res, List<String> path, Map<String, List<String>> map) {
        if (start.equals(end)) {
            res.add(new ArrayList<>(path));
        }
        for (String item : map.get(start)) {
            path.add(item);
            findPath(item, end, res, path, map);
            path.remove(path.size() - 1);
        }
    }

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        helper(s,0,new ArrayList<String>(),res);
        return res;

    }
    private void helper(String s,int l,List<String> cur,List<List<String>> res){
        if(l>=s.length()){
            res.add(new ArrayList<String>(cur));
            return;
        }

        for(int i = l;i<s.length();i++){
            if(l == i){
                cur.add(Character.toString(s.charAt(i)));
                helper(s,i+1,cur,res);
                cur.remove(cur.size()-1);
            }
            else if(isPalindrome(s,l,i)){
                cur.add(s.substring(l,i));
                helper(s,i+1,cur,res);
                cur.remove(cur.size()-1);
            }
        }
    }
    private boolean isPalindrome(String s,int l,int end){
        while(l<end){
            if(s.charAt(l) != s.charAt(end)){
                return false;
            }
            l++;
            end--;
        }
        return true;
    }

    static StringBuilder sb = new StringBuilder();
    public static List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        helper(res,new ArrayList<String>(),0,s,wordDict);
        return res;
    }
    public static void helper(List<String> res,List<String> cur,int pos,String s,List<String> wordDict){
        if(pos == s.length()){
            res.add(toString(cur));
            return;
        }
        for(int i = pos;i<s.length();i++){
            String item = s.substring(pos,i);
            if(wordDict.contains(item)){
                cur.add(item);
                helper(res,cur,i,s,wordDict);
                cur.remove(cur.size()-1);
            }
        }
    }
    public static String toString(List<String> cur){
        if(cur.size() == 1){
            return cur.get(0);
        }
        sb.setLength(0);
        sb.append(cur.get(0));
        for(int i = 1;i < cur.size();i++){
            sb.append(" ");
            sb.append(cur.get(0));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        String[] index = {"hot", "dot", "dog", "lot", "log", "cog"};
//        List<String> res = new ArrayList<>();
//        for (String item:index) {
//            res.add(item);
//        }
        Solution s = new Solution();
//        s.findLadders("hit","log",res);
//        s.partition("aab");
        String[]  a = {"cat","cats","and","sand","dog"};
        List<String> res = new ArrayList<>();
        res.add("cat");
        res.add("cats");
        res.add("and");
        res.add("sand");
        res.add("dog");
        s.wordBreak("catsanddog",res);
    }
}
