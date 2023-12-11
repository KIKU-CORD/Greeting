# アプリケーションについて
部活動で開発したLAN内チャットアプリケーション。    
多くのOSで動作できる「Java」を用いて使用できます。  

同じ部屋での授業や仕事など席を立たなくても、
気軽にやりとりしたいと考え開発を決めました。

チャットに参加できるユーザー数に制限はなく、  
同じネットワーク内でのみ通信ができます。  

通信方法にはマルチキャストを採用しており、  
ネットワークに無駄なトラフィックを生成しません。   

2023/12[更新]：まれに送信できないというバグを修正しました。

# 起動環境について
「Java」がインストールされていれば使用できます。  
推奨している実行環境は「Java 8」バージョンです。（開発環境と同様なため）  
バージョンが複数ある場合はPCの環境変数の設定を必ず行ってください。  

対応OS: Windows (確認済み), MacOS, Linux
# アプリケーションの起動方法
### アプリケーションのダウンロード
このレポジトリの「 [Releases](https://github.com/KIKU-CORD/Greeting/releases/tag/Greeting) 」からZIPをダウンロードします。  
ダウンロードしたら解凍ソフトなどで展開してください。  

**元々は部活動で作成したアプリなので個人情報は伏せています。**  
### JAVAのインストール
このアプリケーションには「Java 8」以上の実行環境が必要です。  
以下のサイトのどちらかからダウンロードしインストールしてください。  
  
[Java.com](https://www.java.com/en/download/)  
[Oracle](https://www.oracle.com/jp/java/technologies/downloads/#java8)  
### アプリケーションの起動方法
解凍したフォルダーを開くといくつか実行ファイルがあります。  
使用しているOSごとの起動方法は以下の通りです。  
  
**Windows**: 「start.bat」をダブルクリックまたは「.jarファイル」をダブルクリック  
**MacOS & Linux**: 「start.sh」をダブルクリック  

※ アクセス許可を求められたら許可を押してください。（通信できなくなります）  
※ ファイアウォールで通信が止められる場合があります。  
### ログイン方法
アプリケーションを起動するとログイン画面が表示されます。  

ユーザー名：自由に設定できますが他人と重複すると一部機能が正しく動作しません。  
パスワード：特に入力は必要ありません。スタッフは指定のパスワードでログインできます。  

**チャットから退出するときは「×」ボタンを押してください。**  
# 機能について
このソフトウェアでは以下の機能が使用可能です。

- デコレーション (文字を修飾できます)
- メンション (指定ユーザーに通知を送ります)
- HTMLモード (HTML構文を送信しチャットに表示します)
- チャット翻訳 (チャットに送られた文章を翻訳します)
- メッセージ翻訳 (メッセージを翻訳してから送信します)
- キック・追放 (スタッフはユーザーをチャットから追放できます)

**⚠ 現在、翻訳APIの不具合によって翻訳機能が動作しなくなっています。**   

使用したAPI：[MTrans API by hujingshuang](https://github.com/hujingshuang/MTrans)  
  
翻訳機能で使える言語は以下のものです。 (この機能は処理が重いので使用はお勧めしません)  
「翻訳元の言語」では、送信された言語を自動判別する機能もついているのでご活用ください。  

日本語, 中国語, 韓国語, 英語, フランス語, スペイン語, ドイツ語, ロシア語,  
イタリア語, ハワイ語, ポルトガル語, インドネシア語, アラビア語, ウクライナ語

詳しくはソフトウェア内の説明画面をご覧下さい。
# アプリケーション画面
![image](https://user-images.githubusercontent.com/109849033/180603413-d9260584-eb67-436d-a648-d3eca091d1da.png)

