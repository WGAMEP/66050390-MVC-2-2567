
import Controller.controller;
import View.*;
import Model.*;
public class main {
    public static void main(String[] args) {
        model m = new model();
        view v = new view(); 
        new controller(v,m);
    }
}
