package com.jay.java.算法.LeetCode;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长 子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class LengthOfLongestSubstring_03 {
    /**
     * 方法一：暴力法：逐个检查所有的子字符串，看它是否不含有重复的字符
     * 算法描述：
     * 1，为了枚举给定字符串的所有子字符串，我们需要枚举它们开始和结束的索引。
     * 假设开始和结束的索引分别为 i 和 j。
     * 那么我们有 0≤i<j≤n（这里的结束索引j是按惯例排除的）。
     * 因此，使用 i 从 0 到 n−1 以及 j 从 i+1 到 n 这两个嵌套的循环，我们可以枚举出 s 的所有子字符串。
     * <p>
     * 2，要检查一个字符串是否有重复字符，我们可以使用集合。
     * 我们遍历字符串中的所有字符，并将它们逐个放入 set 中。
     * 在放置一个字符之前，我们检查该集合是否已经包含它。
     * 如果包含，我们会返回 false。循环结束后，我们返回 true
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/two-sum/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetcod/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring_S1(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        StringBuilder tempS = new StringBuilder();
        for (char c : chars) {
            if (!tempS.toString().contains(String.valueOf(c))) {
                tempS.append(c);
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
        int result = lengthOfLongestSubstring_S1(s);
        System.out.println(result);
    }
}
