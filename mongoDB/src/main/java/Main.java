import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import model.Person;
import org.bson.Document;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MongoCollection<Document> collection = new MongoClient().getDatabase("students").getCollection("grades");
        collection
//                .find(new Document("student_id", 0))
                .find(Filters.eq("student", 0))
//                .projection(new Document("_id", 0))
                .projection(Projections.excludeId())
//                .sort(new Document("score", 1))
                .sort(Sorts.ascending("score"))
                .into(new ArrayList<>())
                .forEach(System.out::println);












        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/users",
                "root",
                "root");
        save(connection, new Person("alena", "dddd"));

        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM  person");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Person> people = new LinkedList<Person>();
        while (resultSet.next()) {
            people.add(new Person(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        }
        people.forEach(System.out::println);
        connection.close();
    }
    public static void save(Connection connection, Person person) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO  person(name, surname) values (?,?)");
        preparedStatement.setString(1, person.getName());
        preparedStatement.setString(2, person.getSurname());
        preparedStatement.executeUpdate();


    }
}
