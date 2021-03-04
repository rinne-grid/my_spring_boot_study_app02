## Todoアプリ

![images](http://www.rinsymbol.sakura.ne.jp/github_images/rngd_todo_20210304.png)

## Spring Bootの学習記録(～2021/02/28)

- TODOアプリの作成
  - [x] ユーザ登録ができること
  - [x] ログインができること
  - [x] TODOの登録ができること
  - [x] TODOの参照ができること
  - [x] TODOの更新ができること: 2021/03/01済
  - [x] TODOの削除ができること: 2021/03/01済
  - [x] TODOの表示条件が指定できること
    - [x] 未完了のタスク: 2021/03/04済
    - [x] 完了のタスク: 2021/03/04済
  - [x] TODOの表示順序を指定できること
     - [x] 開始日の昇順: 2021/03/04済
     - [x] 開始日の降順: 2021/03/04済
     - [x] 終了日の昇順: 2021/03/04済
     - [x] 終了日の降順: 2021/03/04済

## Spring Bootの学習記録(2021/03/05～)
     
- TODOアプリのテスト
  - [ ] テストコードによるテストを実施すること
  - [ ] Github Actionsでプルリクエスト作成時にテストが実行されること
- TODOアプリのPro機能追加
  - [ ] 一般ユーザ・Proユーザの区分変更
    - [ ] 一般ユーザがProユーザに変更できること
    - [ ] Proユーザが一般ユーザに変更できること
    - [ ] Proユーザでプロジェクト機能、CSV登録機能が利用できること
  - [ ] プロジェクト管理ができること
    - [ ] プロジェクトの登録ができること
    - [ ] プロジェクトの参照ができること
    - [ ] プロジェクトの更新ができること
    - [ ] プロジェクトの削除ができること
    - [ ] 既存のTODOをプロジェクトに設定できること
    - [ ] 新たなTODOをプロジェクトに作成できること
  - [ ] CSV登録ができること
    - [ ] CSVファイルからTODOの登録が一括でできること


## 利用までの手順

* 環境構築

```sh
# Hostで実行
$ docker-compose up -d
$ docker-compose exec mysql bash

# Guestで実行
$ mysql -u root -p
$ create database rngd_ss_todo;
$ create user 'springuser'@'%' identified by 'springuser';
$ grant all on my_spring_boot.* to 'springuser'@'%';
```

* SpringBootアプリを起動

* 以下のURLにアクセス

```
http://localhost:8080/rngd
```


#### 備忘録

##### ユーザ登録と同時にログイン画面に遷移する

* WebSecurityConfigでauthenticationManagerBeanをオーバーライドし、AuthenticationManagerを返すようにする
  
```java:WebSecurityConfig.java
    // WebSecurityConfig.java 70行目
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
```

* AppUserManagerServiceクラスにAuthenticationManagerを注入
  * UsernamePasswordAuthenticationTokenを生成。
  * authToken.setDetailsで、WebAuthenticationDetails(request)のインスタンスを渡す
  * Authenticationを生成。authenticationManager.authenticate(authToken)
  * SecurityContextHolder.getContext().setAuthentication(authentication)

```java
    // AppUserManagerService.java 50行目
    public void authWithAuthManager(HttpServletRequest request, String user, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, password);
        authToken.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

```

* authWithAuthManagerを呼び出す

```java
  // AuthController 47行目
  appUserManagerService.authWithAuthManager(request, user, password);
```

##### アプリユーザ（認証済）を取得する

```java
    // AppUserManagerService.java 59行目
    public AppUserModel getAppUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String userName = authentication.getName();
        return rep.findByUsername(userName);
        
    }
```


##### ManyToOneでリレーションを貼る(TodoModel <- AppUserModel)

```java
    // TodoModel.java 50行目
    @ManyToOne
    private AppUserModel user;
```

##### 特定の条件でデータを検索する


```java
    // TodoModelRepository.java
    List<TodoModel> findByUser(AppUserModel user);
```



