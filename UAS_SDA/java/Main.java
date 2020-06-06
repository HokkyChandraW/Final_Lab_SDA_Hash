import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Map<String, ArrayList<String>> map = new Map<>();
        ArrayList<String> all = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String strLine = "";
        // Opens a data file
        try {
            BufferedReader br = new BufferedReader(new FileReader("../file/data.txt"));
            while (strLine != null) {
                // Read the data one by one
                strLine = br.readLine();
                if (strLine != null) {
                    String[] data = strLine.split(":", 2);

                    // Cut the data and save it into hash
                    if (map.get(data[0]) == null) // if the data doesnt exist, it'll save it in new hash element
                    {
                        ArrayList<String> temp = new ArrayList<>();
                        temp.add(data[1]);
                        map.add(data[0], temp);
                        all.add(data[0]);
                    } else { // if the data exist, append the existing hash element
                        map.get(data[0]).add(data[1]);
                    }
                }
            }
            br.close();

            // Handle the exception of possible outcomes
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Unable to read the file.");
        }

        // if the argument says "show" then it'll print every value into html page
        if (args[0].equals("show")) {
            Collections.sort(all);
            System.out.println("<table class='table'>");
            System.out.println("<tr><th>No</th>");
            System.out.println("<th>Nama Editor </th>");
            System.out.println("<th>Jumlah Artikel </th></tr>");

            for (int i = 0; i < all.size(); i++) {
                ArrayList<String> e = map.get(all.get(i));
                System.out.println("<tr><td>" + (i + 1) + "</td>");
                System.out.println("<td>" + all.get(i) + "</td>");
                System.out.println("<td>" + e.size() + "</td></tr>");
            }
            System.out.println("</table>");

            // if argument was not 'show', and its an editor name then get the data from
            // hash
            // and print it into HTML format
        } else {
            ArrayList<String> result = map.get(args[0]);
            if (result != null) // if data was founded print it
            {
                Collections.sort(result);
                System.out.println("<h5 class='mb-3'>Daftar artikel dengan editor " + args[0] + " </h5>");
                System.out.print("<ul class='list-group'>");
                result.forEach(
                        (e) -> System.out.print("<li class='list-group-item list-group-item-action'>" + e + "</li>"));
                System.out.print("</ul>");
            } else // status: 404
            {
                System.out.println("Data For " + args[0] + " Not Found");
            }
        }
    }
}