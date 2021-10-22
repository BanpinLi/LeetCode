package src;

// 字典树，对于某种比较极端的情况似乎并没有考虑进行优化
class WordDictionary {
    private Trie root;

    public WordDictionary() {
        root = new Trie();
    }

    public void addWord(String word) {
        Trie node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.nodes[ch - 'a'] == null) {
                node.nodes[ch - 'a'] = new Trie();
            }
            node = node.nodes[ch - 'a'];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        return dfs(root, word, 0);
    }

    // 给一个node作为根节点，查找s在不在这里面
    private boolean dfs(Trie node, String s, int depth) {
        if (node == null) {
            return false;
        }
        if (depth == s.length()) {
            return node.isEnd;
        }

        char ch = s.charAt(depth);
        if (ch == '.') {
            for (int i = 0; i < 26; i++) {
                if (dfs(node.nodes[i], s, depth + 1)) {
                    return true;
                }
            }
        } else {
            return dfs(node.nodes[ch - 'a'], s, depth + 1);
        }
        return false;
    }

    class Trie {
        public boolean isEnd;
        public Trie[] nodes;

        public Trie() {
            nodes = new Trie[26];
            isEnd = false;
        }
    }
}