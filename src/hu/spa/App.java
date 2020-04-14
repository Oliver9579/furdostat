package hu.spa;

import hu.spa.controller.SpaService;
import hu.spa.model.service.*;

public class App {

    private final SpaService spaService;
    private final FileWriter fileWriter;

    private App() {
        DataApi dataApi = new DataApi(new FileReader(),  new DataParser(), new GuestLogTransformer());
        spaService = new SpaService(dataApi.getData("furdoadat.txt"));
        fileWriter = new FileWriter("szauna.txt");
    }

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println("2.feladat");
        System.out.println(spaService.getFirstAndLastGuestTime());
        System.out.println("3.feladat");
        System.out.println("A fürdőben " + spaService.countSingleDepartmentVisit() + " vendég járt csak egy részlegen. ");
        System.out.println("4.feladat");
        System.out.println("A legtöbb időt elöltő vendég: \n" + spaService.getLongestStayGuestDetail());
        System.out.println("5.feladat");
        System.out.println(spaService.getGuestStatisticByHour());
        fileWriter.write(spaService.getTotalTimeInSauna());
        System.out.println("7.feladat");
        System.out.println(spaService.getDepartmentVisitStatistic());
    }
}
