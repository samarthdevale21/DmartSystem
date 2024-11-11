import java.util.ArrayList;
import java.util.Scanner;

class Dmart {
    String address;
    long contact;
    String email;
    String website;
    ArrayList<Product> productList = new ArrayList<>();
    Cashier cashier;
    Customer customer;
    Cart cart;

    Dmart(String address, long contact, String email, String website) {
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.website = website;

    }

    public void displayDmart() {
        System.out.println();
        System.out.println("Address :" + address);
        System.out.println("contact :" + contact);
        System.out.println("email :" + email);
        System.out.println("Website : " + website);
    }

    public void AddProduct(String cate, String name, double orgPrice, Barcode barcode) {
        Product Product = new Product(cate, name, orgPrice, barcode);
        productList.add(Product);

    }

    public void addCart(String type) {
        cart = new Cart(type);

    }

    public void addCashier(String name, String id, long contact, int counterNum) {
        cashier = new Cashier(name, id, contact, counterNum);

    }

    public void addCustomer(String name, long contact) {
        if (cashier != null && productList.size() != 0) {
            customer = new Customer(name, contact);
        } else {
            System.out.println("add cahier and product first ");
        }
    }
}

class Product {
    String cate;
    String name;
    Barcode barcode;
    double orgPrice;

    Product(String cate, String name, double orgPrice, Barcode barcode) {
        this.cate = cate;
        this.name = name;
        this.orgPrice = orgPrice;
        this.barcode = barcode;

    }

    public void displayProduct() {
        System.out.println("Barcode [ Cate : ]" + cate + ",Name :" + name + ",Orginal Price :" + orgPrice + "]");
    }

    public void addBarcode(String productId, double Price) {
        barcode = new Barcode(productId, Price);
    }
}

class Barcode {
    String ProductId;
    double price;

    Barcode(String productId, double price) {
        this.ProductId = productId;
        this.price = price;
    }

    public void displayBarcode() {
        System.out.println("Barcode [ProductID :" + ",Price:" + price + "]");
    }
}

class Customer {
    String name;
    long contact;
    String paymentMode;
    String billNo;
    double totalBill;
    ArrayList<Product>customerCart=new ArrayList<>();

    Customer(String name, long contact) {
        this.name = name;
        this.contact = contact;
        
    }

    public void displayCustomer() {
        System.out.println("Customer [Name : " + name + ",Contact :" + contact + ",PaymentMode :" + ",Bill No" + billNo
                + ",Total bill:" + totalBill + "]");
    }
}

class Cashier {
    String name;
    String id;
    long contact;
    int counterNum;

    Cashier(String name, String id, long contact, int counterNum) {
        this.name = name;
        this.id = id;
        this.contact = contact;
        this.counterNum = counterNum;
    }

    public void displayCashier() {
        System.out.println("Cashier[name:" + name + ",id:" + id + ",Contaact : " + ",CunterNumer :" + counterNum + "]");

    }
}

class Cart {
    String type;
    ArrayList<Product>cart=new ArrayList<>();
    Cart(String type){
        this.type=type;
    }
    public void displayCart(){
        System.out.println("Cart [Type :"+type+",capacity:"+cart.size());
        for(Product i:cart){
            i.displayProduct();
            i.barcode.displayBarcode();
        }
    }
}

class DmartDriver {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Dmart dmart=new Dmart("Deccan", 9565875215l, "dmart@gmail.com", "www.dmart.com");
        dmart.displayDmart();
        for(int i=1;i<=2;i++){
            System.out.println("*****ADD PRODUCT*****");
            System.out.println("Cate :");
            sc.nextLine();
            String cate=sc.nextLine();
            System.out.println("Product Name :");
            String name=sc.nextLine();
            System.out.println("MRP :");
            double price=sc.nextDouble();
            System.out.println("Product ID :");
            sc.nextLine();
            String id=sc.nextLine();
            System.out.println("Dmart Price:");
            double dprice=sc.nextDouble();
            dmart.AddProduct(cate, name, price, (new Barcode(id, dprice)));
        }
        for(Product i :dmart.productList){
            i.displayProduct();
            i.barcode.displayBarcode();
        }
        dmart.addCashier("Ramesh kumar", "DCAH123", 9878462903l, 2);
        dmart.cashier.displayCashier();
        dmart.addCart("basket");
        dmart.cart.displayCart();
        dmart.addCustomer("Suresh kumar", 9988742355l);
        dmart.customer.displayCustomer();
        for(;;){
            System.out.println("Enter the Product id");
            sc.nextLine();
            String pid=sc.nextLine();
            boolean flag=false;
            for(Product i:dmart.productList){
                if(pid.equals(i.barcode.ProductId)){
                    dmart.customer.customerCart.add(i);
                    flag=true;
                }
            }
            if(!flag){
                System.out.println("Product Not Found");
            }
            System.out.println("Do u Want to continue shopping(Y/N) :");
            char ch=sc.next().charAt(0);
            if(ch=='Y'){
                continue;
            }else{
                break;
            }
        }
        double bill=0;
        for(Product i :dmart.customer.customerCart){
            i.displayProduct();
            i.barcode.displayBarcode();
            dmart.customer.totalBill+=i.barcode.price;
            bill+=i.orgPrice;
        }
        System.out.println("Total Bill is :"+bill);
        System.out.println("Discounted price :"+dmart.customer.totalBill);
    }
}
