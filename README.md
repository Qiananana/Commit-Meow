# 🐾 Commit-Meow: Your CLI Evolution Pet

> "A cat that breathes through your commits."

`Commit-Meow` is a gamified GitHub contribution tracker that lives in your terminal. It monitors your daily coding activity and reflects it through a dynamic, ASCII-art cat.

---

### 🐈 The Evolution States

| Commits | State | Cat Preview |
| :--- | :--- | :--- |
| **0** | **Sleeping** | `(zZz)` 💤 |
| **1-3** | **Walking** | `( ^.^)` 🐾 |
| **4+** | **Party** | `(o.o)  MEOW!!` 🔥 |

---

### 🚀 How to Run locally

1. **Get your Token**: Generate a [GitHub Classic Token](https://github.com/settings/tokens).
2. **Set Environment Variable**:
   - Windows: `setx GITHUB_TOKEN "your_token_here"`
3. **Run**:
   ```bash
   java -jar CommitMeow.jar
