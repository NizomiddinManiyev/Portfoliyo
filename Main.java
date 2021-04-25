import model.Card;
import service.CardServiceImplement;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardServiceImplement cardServiceImplement = new CardServiceImplement();
        Card currentCard = null;
    while (true){
        System.out.println("Karta id raqamini kiriting");
        String cardId = scanner.nextLine();
        for (Card card : cardServiceImplement.getCards()) {
            if (card.getId().equals(cardId)){
                currentCard=card;
            }
        }
        if (currentCard!=null){
            if(cardServiceImplement.checkPin(currentCard)){
                if (currentCard.isState()){
                    while(true){
                        System.out.println("0=>Exit Card\n1=>CheckBalance\n2=>Withdraw\n3=>Change Password\n4=>Transfer");
                        scanner=new Scanner(System.in);
                        switch (scanner.nextInt()){
                            case 0:return;
                            case 1:
                                cardServiceImplement.checkBalance(currentCard);
                                break;
                            case 2:
                                double withDraw=0;
                                scanner=new Scanner(System.in);
                                System.out.println("0=>50ming,1=>100ming,2=>200ming,3=>500ming,4=>boshqa summa");
                                switch (scanner.nextInt()){
                                    case 0:
                                        withDraw=50000;
                                        break;
                                    case 1:
                                        withDraw=100000;
                                        break;
                                    case 2:
                                        withDraw=200000;
                                        break;
                                    case 3:
                                        withDraw=500000;
                                        break;
                                    case 4:
                                        scanner=new Scanner(System.in);
                                        System.out.println("Naqd pul miqdorini kiriting...");
                                        withDraw=scanner.nextDouble();

                                }
                                cardServiceImplement.withdraw(currentCard,withDraw);
                                break;
                            case 3:
                                cardServiceImplement.changePassword(currentCard);
                                break;
                            case 4:
                                Card receiverCard=null;
                                scanner=new Scanner(System.in);
                                System.out.println("Qabul qiluvchi karta id sini kiriting");
                                String cardsId=scanner.nextLine();
                                for (Card card : cardServiceImplement.getCards()) {
                                    if (card.getId().equals(cardsId)){
                                        receiverCard=card;
                                    }
                                }
                                if(receiverCard!=null){
                                    if (!receiverCard.getId().equals(currentCard.getId())){
                                        cardServiceImplement.transfer(currentCard,receiverCard);
                                    }else {
                                        System.out.println("O'zingizga pul o'tkaza olmaysiz");
                                    }

                                }else {
                                    System.out.println("Bunday karta mavjud emas");
                                }


                        }
                    }

                }else {
                    System.out.println("Kartangiz blocklangan!!!");
                }
            }
        }else {
            System.out.println("Bunday card mavjud emas");
        }
    }
    }
}
