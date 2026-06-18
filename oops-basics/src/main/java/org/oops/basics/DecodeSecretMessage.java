package org.oops.basics;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecodeSecretMessage {

    static class Cell {
        char ch;
        int x;
        int y;

        Cell(char ch, int x, int y) {
            this.ch = ch;
            this.x = x;
            this.y = y;
        }
    }

    public static void printSecretMessage(String urlString) throws Exception {

        StringBuilder html = new StringBuilder();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        URI.create(urlString).toURL().openStream(),
                        StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                html.append(line).append("\n");
            }
        }

        // Extract all td values
        Pattern pattern = Pattern.compile("<td[^>]*>(.*?)</td>",
                Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

        Matcher matcher = pattern.matcher(html.toString());

        List<String> values = new ArrayList<>();

        while (matcher.find()) {
            String value = matcher.group(1)
                    .replaceAll("<[^>]+>", "")
                    .trim();

            values.add(value);
        }

        List<Cell> cells = new ArrayList<>();

        int maxX = 0;
        int maxY = 0;

        // Skip header row:
        // x-coordinate | Character | y-coordinate
        for (int i = 3; i + 2 < values.size(); i += 3) {

            int x = Integer.parseInt(values.get(i));
            char ch = values.get(i + 1).charAt(0);
            int y = Integer.parseInt(values.get(i + 2));

            cells.add(new Cell(ch, x, y));

            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
        }

        char[][] grid = new char[maxY + 1][maxX + 1];

        for (int y = 0; y <= maxY; y++) {
            for (int x = 0; x <= maxX; x++) {
                grid[y][x] = ' ';
            }
        }

        for (Cell cell : cells) {
            grid[cell.y][cell.x] = cell.ch;
        }

        for (int y = 0; y <= maxY; y++) {
            System.out.println(new String(grid[y]));
        }
    }

    public static void main(String[] args) throws Exception {

        String url =
                "https://docs.google.com/document/d/e/2PACX-1vSvM5gDlNvt7npYHhp_XfsJvuntUhq184By5xO_pA4b_gCWeXb6dM6ZxwN8rE6S4ghUsCj2VKR21oEP/pub";

        printSecretMessage(url);
    }
}