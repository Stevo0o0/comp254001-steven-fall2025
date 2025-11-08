package comp254.lab3.ex3;

import java.io.File;
import java.util.Scanner;

public class FileFinder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter directory path: ");
        String path = sc.nextLine().trim();

        System.out.print("Enter name to search: ");
        String query = sc.nextLine().trim().toLowerCase();

        System.out.println("\nSearching for \"" + query + "\" under " + path + "...\n");
        int found = search(new File(path), query);
        System.out.println(found == 0 ? "No matches found." : "\nTotal matches: " + found);
        sc.close();
    }

    private static int search(File dir, String query) {
        int count = 0;
        File[] files = dir.listFiles();
        if (files == null) return 0;

        for (File f : files) {
            String name = f.getName().toLowerCase();
            if (name.contains(query)) {
                System.out.println((f.isDirectory() ? "[DIR ] " : "[FILE] ") + f.getAbsolutePath());
                count++;
            }
            if (f.isDirectory()) count += search(f, query);
        }
        return count;
    }
}
