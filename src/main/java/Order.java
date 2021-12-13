import com.github.javafaker.Faker;
import io.qameta.allure.Step;

import java.text.SimpleDateFormat;
import java.util.Calendar;



public class Order {

    public static Faker faker = new Faker();

    public String firstName;
    public String lastName;
    public String address;
    public int metroStation;
    public String phone;
    public int rentTime;
    public String deliveryDate;
    public String comment;
    public ScooterColor[] color;


    public Order(String firstName, String lastName, String address, int metroStation,
                 String phone, int rentTime, String deliveryDate, String comment, ScooterColor[] color){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public static Order getFaker(){
         String firstName = faker.name().firstName();
         String lastName = faker.name().lastName();
         String address = faker.address().streetAddress();
         int metroStation = faker.number().numberBetween(1,101);
         String phone = faker.phoneNumber().phoneNumber();
         int rentTime = faker.number().numberBetween(1,365);
         String deliveryDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
         String comment = faker.superhero().name();
        ScooterColor[] color = new ScooterColor[]{};
        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }

    public Order setColor(ScooterColor[] color){
        this.color = color;
        return this;
    }

}
