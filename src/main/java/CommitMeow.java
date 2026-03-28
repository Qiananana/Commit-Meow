import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;

public class CommitMeow {

    public static void main(String[] args) {
        System.out.println("--- Commit-Meow System Starting ---");

        // 1. Use that "counting machine" to obtain the actual numbers
        int todayCommits = getCommitCountFromGitHub();

        // 2. According to the figures, different cats
        if (todayCommits == 0) {
            System.out.println("Status: [SLEEPING] 💤");
            System.out.println("      |\\      _,,,---,,_");
            System.out.println("ZZZzz /,`.-'`'    -.  ;-;;,_");
            System.out.println("     |,4-  ) )-,_. ,\\ (  `'-'");
            System.out.println("    '---''(_/--'  `-'\\_)  ");
            System.out.println("The cat is disappointed. No commits today?");
        } else if (todayCommits <= 3) {
            System.out.println("Status: [WALKING] 🐾");
            System.out.println("     |\\__/,|   (`\\");
            System.out.println("   _.|o o  |_   ) )");
            System.out.println("-(((---(((--------");
            System.out.println("You have " + todayCommits + " commits. Keep going, human!");
        } else {
            System.out.println("Status: [CYBER-RAVE] 🔥");
            System.out.println("      _                _ ");
            System.out.println("     /_\\              /_\\");
            System.out.println("    (o.o)    Meow!   (o.o)");
            System.out.println("   __(U)__  PARTY!!  __(U)__");
            System.out.println("Wow! " + todayCommits + " commits! The cat is dancing!");
        }
    }

    public static int getCommitCountFromGitHub() {
        OkHttpClient client = new OkHttpClient();

        String myToken = System.getenv("GITHUB_TOKEN");

        Request request = new Request.Builder()
                .url("https://api.github.com/users/Qiananana/events")
                .addHeader("Authorization", "Bearer " + myToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String jsonData = response.body().string();

                // --- Here is the analysis of the logic. ---
                JsonArray events = JsonParser.parseString(jsonData).getAsJsonArray();
                int count = 0;
                String today = LocalDate.now().toString(); // Get today's date

                for (int i = 0; i < events.size(); i++) {
                    // Check the type and time of each record.
                    String type = events.get(i).getAsJsonObject().get("type").getAsString();
                    String createdAt = events.get(i).getAsJsonObject().get("created_at").getAsString();

                    if (type.equals("PushEvent") && createdAt.startsWith(today)) {
                        count++;
                    }
                }
                return count;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return 0; // If it fails, return 0 and let the cat sleep.
    }
}