# Galaxy
# ギャラクシー (Galaxy) 🌌

NASAのAPIを活用して、「今日の宇宙画像」の閲覧や検索、お気に入りの保存ができるWebアプリケーションです。

---

 　**主な機能**

- **今日の宇宙画像表示**  
  NASAが提供する「Astronomy Picture of the Day (APOD)」を取得し、本日の宇宙画像を表示します。
- **ランダム表示**  
  過去の宇宙画像をランダムに呼び出して新しい発見を楽しむことができます。
- **日付検索**  
  カレンダーなどで指定した日付の宇宙画像を表示できます。
- **お気に入り保存**  
  気に入った宇宙画像のタイトル、URL、日付、説明文を保存できます。
- **メモ機能・管理**  
  保存したお気に入り画像に対してオリジナルのメモを記入・更新したり、不要になった画像を削除できます。
  **ログイン**
  名前　user   パス　psaa
---

## 🛠 使用技術

- **バックエンド:** Java / Spring Boot
- **テンプレートエンジン:** Thymeleaf
- **外部API:** NASA API (Astronomy Picture of the Day)
- **データベース:** Spring Data JPA (H2 / MySQL など)

---

## 💻 動作方法 (ローカル実行)

### 必須要件
- Java 17 以上
- Gradle または Maven

### 実行手順
1. リポジトリをクローンします。
   ```bash
   git clone [https://github.com/tarabagani-621/Galaxy.git](https://github.com/tarabagani-621/Galaxy.git)

   ## デプロイ先URL
https://galaxy-c3nk.onrender.com
