package org.example;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.EncodingType;
import com.knuddels.jtokkit.api.IntArrayList;

import java.util.List;

/*
    The closest Java alternative is JTokkit, a Java library that implements OpenAI's
     tiktoken-compatible tokenization.
 */
public class OpenAITokenizationExample {

    public static void main(String[] args) {

        EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();

        // GPT-4.1-mini uses the o200k_base encoding
        Encoding encoding = registry.getEncoding(EncodingType.O200K_BASE);

        String text = "Hi my name is Rahul and I am learning about LLM models";

        // Convert text into token IDs
        List<Integer> tokens = encoding.encode(text).boxed();

        // Print each token ID and its decoded text
        for (int tokenId : tokens) {
            IntArrayList singleToken = new IntArrayList();
            singleToken.add(tokenId);

            String tokenText = encoding.decode(singleToken);
            System.out.println(tokenId + " = " + tokenText);
        }

        // Decode token ID 326
        IntArrayList token = new IntArrayList();
        token.add(326);
        System.out.println("Token 326 = " + encoding.decode(token));
    }
}
