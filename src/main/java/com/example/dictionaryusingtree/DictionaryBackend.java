package com.example.dictionaryusingtree;

import java.util.HashMap;

public class DictionaryBackend {
    private HashMap<String,String> wordMapper;

    DictionaryBackend(){
        wordMapper=new HashMap<>();
        addWord("apple", "a red color fruit");
        addWord("orange", "a orange colored fruit");
        addWord("cow", "a holy animal which gives us milk");
        addWord("money", "an exchange token for services");
        addWord("fun", "enjoyment");
        addWord("pillow", "a soft item to sleep on");
        addWord("tie", "a formal short cloth wore around neck");
        addWord("devolious","a brand");
    }

    public boolean addWord(String word,String meaning){
        wordMapper.put(word,meaning);
        return true;
    }

    public void connectToDatabase(){
        final String DB_URL = "jdbc:mysql://localhost:3306/dictionarydb";
        final String USER = "root";
        final String PASS = "lipsa@1*";

        System.out.println("Connecting to database");
        String newId = "select * from word_list";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(newId);
        ) {
            while (rs.next()) {
                //Display values
                System.out.println(rs.getInt("id") + rs.getString("word") + rs.getString("meaning")); //rs.getInt("rollno");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String findMeaning(String word){
        if(!wordMapper.containsKey(word)){
            return "Word not found!";
        }
        return wordMapper.get(word);
    }
}
