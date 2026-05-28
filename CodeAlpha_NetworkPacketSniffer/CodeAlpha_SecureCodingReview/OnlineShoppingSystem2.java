import java.awt.*;
import javax.swing.*;

public class OnlineShoppingSystem2 {

    static JLabel cartLabel, billLabel, paymentLabel;

    static String selectedProduct = "";

    public static void main(String[] args) {

        String username = JOptionPane.showInputDialog("Enter Username");

        String password = JOptionPane.showInputDialog("Enter Password");

        if(username.equals("admin") && password.equals("1234")) {

            JFrame frame = new JFrame("🛒 Online Shopping System");

            frame.setSize(850,700);

            frame.setLayout(new GridLayout(15,1,10,10));

            frame.getContentPane().setBackground(
                    new Color(230,240,255)
            );

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel title = new JLabel(
                    "🛍 ONLINE SHOPPING SYSTEM",
                    SwingConstants.CENTER
            );

            title.setFont(new Font("Arial",Font.BOLD,30));

            frame.add(title);

            JLabel login = new JLabel(
                    "✅ Login Successful",
                    SwingConstants.CENTER
            );

            login.setFont(new Font("Arial",Font.BOLD,18));

            frame.add(login);

            JLabel recommend = new JLabel(
                    "🔥 Highly Recommended Products",
                    SwingConstants.CENTER
            );

            recommend.setFont(new Font("Arial",Font.BOLD,22));

            frame.add(recommend);

            addButton(frame,"Laptop",50000,
                    "⭐ Best For Developers",
                    new Color(70,130,180));

            addButton(frame,"Mobile Phone",20000,
                    "🔥 Trending Product",
                    new Color(46,139,87));

            addButton(frame,"Headphones",2000,
                    "🎵 Best Audio Experience",
                    new Color(186,85,211));

            addButton(frame,"Smart Watch",5000,
                    "⌚ Fitness Recommended",
                    new Color(255,140,0));

            addButton(frame,"Keyboard",1500,
                    "⌨ Gaming Keyboard",
                    new Color(220,20,60));

            addButton(frame,"Mouse",800,
                    "🖱 Wireless Mouse",
                    new Color(72,61,139));

            cartLabel = new JLabel(
                    "🛒 Cart Empty",
                    SwingConstants.CENTER
            );

            cartLabel.setFont(new Font("Arial",Font.BOLD,18));

            frame.add(cartLabel);

            billLabel = new JLabel(
                    "Total Bill: Rs 0",
                    SwingConstants.CENTER
            );

            billLabel.setFont(new Font("Arial",Font.BOLD,18));

            frame.add(billLabel);

            paymentLabel = new JLabel(
                    "Waiting for billing...",
                    SwingConstants.CENTER
            );

            paymentLabel.setFont(new Font("Arial",Font.BOLD,18));

            frame.add(paymentLabel);

            JButton proceed = new JButton("Proceed To Billing");

            proceed.setBackground(Color.BLACK);

            proceed.setForeground(Color.WHITE);

            proceed.setFont(new Font("Arial",Font.BOLD,20));

            proceed.addActionListener(e -> {

                if(selectedProduct.equals("")) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "⚠ Select Product First"
                    );

                    return;
                }

                try {

                    paymentLabel.setText(
                            "🔍 Analyzing Product..."
                    );

                    Thread.sleep(2000);

                    paymentLabel.setText(
                            "🛒 Adding To Secure Cart..."
                    );

                    Thread.sleep(2000);

                    paymentLabel.setText(
                            "💰 Processing Billing..."
                    );

                    Thread.sleep(2000);

                    int approval =
                            JOptionPane.showConfirmDialog(
                                    frame,
                                    "Use saved card for payment?",
                                    "Payment Approval",
                                    JOptionPane.YES_NO_OPTION
                            );

                    if(approval == JOptionPane.YES_OPTION) {

                        paymentLabel.setText(
                                "💳 Processing Payment..."
                        );

                        Thread.sleep(2500);

                        paymentLabel.setText(
                                "✅ Payment Successful using XXXX-XXXX-XXXX-5678"
                        );

                        JOptionPane.showMessageDialog(
                                frame,
                                "🎉 Order Placed Successfully!"
                        );

                    } else {

                        paymentLabel.setText(
                                "❌ Payment Cancelled"
                        );
                    }

                } catch(Exception ex) {

                    ex.printStackTrace();
                }
            });

            frame.add(proceed);

            frame.setVisible(true);

        } else {

            JOptionPane.showMessageDialog(
                    null,
                    "❌ Invalid Username or Password"
            );
        }
    }

    static void addButton(
            JFrame frame,
            String product,
            int price,
            String recommend,
            Color color
    ) {

        JButton button = new JButton(
                product + " - Rs " + price +
                        " | " + recommend
        );

        button.setBackground(color);

        button.setForeground(Color.WHITE);

        button.setFont(new Font("Arial",Font.BOLD,16));

        button.addActionListener(e -> {

            selectedProduct = product;

            cartLabel.setText(
                    "✔ " + product + " added to cart"
            );

            billLabel.setText(
                    "Total Bill: Rs " + price
            );
        });

        frame.add(button);
    }
}