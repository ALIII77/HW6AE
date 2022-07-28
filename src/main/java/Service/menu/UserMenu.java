package Service.menu;

import Entity.Article;
import Entity.User;
import Repository.ArticleRepository;
import Repository.UserRepository;
import Service.ApplicationConstant;
import Service.Printer;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

public class UserMenu {
    public static void runFirstMenu() throws SQLException, ParseException {
        System.out.println("WELCOME");
        while (true) {
            Printer.print(ApplicationConstant.USER_MENU);
            System.out.print("Enter your choice : ");
            int input = ApplicationConstant.getInput().nextInt();
            switch (input) {
                case 1:
                    login();
                    break;
                case 2:
                    signup();
                    break;
                case 3:
                    viewArticles();
                    break;

            }

        }
    }

    public static void login() throws SQLException {
        UserRepository userRepository = new UserRepository();

        User user = new User();
        System.out.print("Enter your username for login : ");
        String inputUsername = ApplicationConstant.getInput().next();
        if (ApplicationConstant.getUserRepository().isUsernameExist(inputUsername)) {
            user= userRepository.returnIDUser(inputUsername);
            System.out.println(user.toString());
            System.out.println("Enter your password");
            String inputPassword = ApplicationConstant.getInput().next();


            if (inputPassword.equals(user.getPassword()))
            {
                System.out.println("okeye dada biatoo");
            }

        }


    }

    public void afterLogin(User user) {

    }

    public static void signup() throws SQLException, ParseException {
        User user = new User();
        UserRepository userRepository = new UserRepository();

        while (true) {
            System.out.print("Enter username : ");
            String username = ApplicationConstant.getInput().next();
            if (!ApplicationConstant.getUserRepository().isUsernameExist(username)) {
                user.setUsername(username);
                System.out.println();
                System.out.print("Enter your national code : ");
                String nationalCode = ApplicationConstant.getInput().next();
                user.setNationalCode(nationalCode);
                user.setPassword(nationalCode);
                System.out.print("Enter your birthday (yyy/mm/dd) : ");
                String date = ApplicationConstant.getInput().next();
                user.setBirthday(date);
                userRepository.createdUser(user);

                break;
            } else {
                System.out.println("this username is exist");
                continue;
            }

        }


//        System.out.print("Enter your firstname : ");
//        String nationalCode = ApplicationConstant.getInput().next();
//        user.setNationalCode(nationalCode);
//        System.out.print("Enter your birth day (YYYY/MM/DD) : ");
//        String date = ApplicationConstant.getInput().next();
//        user.setBirthday(date);
    }

    public static void viewArticles() throws SQLException {
        List<Article> articles = ArticleRepository.allOfArticle();
        for (int i = 0; i < ArticleRepository.allOfArticle().size(); i++) {
            Article article = articles.get(i);
            System.out.print(article.getId());
            System.out.println(" - " + article.getTitle());
        }
        System.out.print("now enter id for get this text : ");
        int input = ApplicationConstant.getInput().nextInt();
        Article articleChoose = articles.get(input);
        System.out.println(articleChoose.getText());
    }
}
