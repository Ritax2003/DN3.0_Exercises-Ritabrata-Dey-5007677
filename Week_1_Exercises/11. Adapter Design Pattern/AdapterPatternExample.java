interface PaymentProcessor {
    void processPayment(double amount);
}
class PayPalGateway {
    public void makePayment(double amount) {
        System.out.println("Processing payment of $" + amount + " through PayPal.");
    }
}


class StripeGateway {
    public void pay(double amount) {
        System.out.println("Processing payment of $" + amount + " through Stripe.");
    }
}


class SquareGateway {
    public void process(double amount) {
        System.out.println("Processing payment of $" + amount + " through Square.");
    }
}
class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway payPalGateway;

    public PayPalAdapter() {
        this.payPalGateway = new PayPalGateway();
    }

    @Override
    public void processPayment(double amount) {
        payPalGateway.makePayment(amount);
    }
}

class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripeGateway;

    public StripeAdapter() {
        this.stripeGateway = new StripeGateway();
    }

    @Override
    public void processPayment(double amount) {
        stripeGateway.pay(amount);
    }
}

class SquareAdapter implements PaymentProcessor {
    private SquareGateway squareGateway;

    public SquareAdapter() {
        this.squareGateway = new SquareGateway();
    }

    @Override
    public void processPayment(double amount) {
        squareGateway.process(amount);
    }
}

public class AdapterPatternExample {
    public static void main(String[] args) {
        PaymentProcessor payPalProcessor = new PayPalAdapter();
        PaymentProcessor stripeProcessor = new StripeAdapter();
        PaymentProcessor squareProcessor = new SquareAdapter();

        payPalProcessor.processPayment(100.00);
        stripeProcessor.processPayment(150.50);
        squareProcessor.processPayment(200.75);
    }
}
