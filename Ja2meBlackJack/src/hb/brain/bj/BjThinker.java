package hb.brain.bj;

import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.*;

/**
 * @author hubin
 */
public class BjThinker extends MIDlet {

    private Display display;
    private Command exit;
    private int[] dealer = new int[12];
    private int[] playAa = new int[12];
    private int[] playBb = new int[12];
    private int[] playCc = new int[12];
    private String strDealer = "庄家";
    private String strPlayAa = "玩A";
    private String strPlayBb = "玩B";
    private String strPlayCc = "玩C";
    private int count = 0;
    private Vector deck = new Vector();
    private int deckIndex = 0;
    private StringBuffer sbDealer = new StringBuffer("hello, java!");
    private int activeIndex = 1;
    private int activeIndexPlus = 1;
    private boolean isBat = true;
    private StringBuffer consoleStr1line = new StringBuffer("");
   //no use

    private StringBuffer displayString = new StringBuffer("");
    private StringBuffer sbPlaya = new StringBuffer("hello, java!");
    private StringBuffer sbPlayb = new StringBuffer("hello, java!");
    private StringBuffer sbPlayc = new StringBuffer("hello, java!");
    private StringBuffer batString = new StringBuffer("");
    private StringBuffer batStringEvenOdd = new StringBuffer("");
    private boolean ready4input = false;
    private int[] diceArr = new int[3];
    private int index = 0;
    private int screen = 210;
    private boolean islight = false;
    private String charSep = "|";
    private String onString = "|开|";
    private String offString = "|关|";
    private String bigString = "大";
    private String smlString = "小";
    private String evenString = "双";
    private String sixLi = "粒六";
    private String wuChu = "无出";
    private String siZong = "四总";
    private String quanYi = "全一";
    private String erWu = "二五";
    private String sanSi = "三四";
    private String daHe = "大和";
    private String xiaoHe = "小和";
    private String baoZi = "豹子";
    private String oddString = "单";
    private String continueString = "连";
    private String moreString = "多";
    private String tieString = "平";
    private String threeString = "三";
    private String doubleString = "对";
    private String canbetString = "可押";
    private String emptyString = " ";
    private String leftBlock = "[";
    private String rightBlock = "]";
    private String appPausedString = "App paused.";
    private Random random = new Random();

    public BjThinker() {
        display = Display.getDisplay(this);
    }

    public void destroyApp(boolean unconditional) {
    }

    public void pauseApp() {
        consoleStr1line.delete(0, consoleStr1line.length());
        consoleStr1line.append(appPausedString);
    }

    public void startApp() {
        display = Display.getDisplay(this);
        Canvas canvas = new Canvas() { // anonymous class

            private int tmpSumHolder;

            public void paint(Graphics graphics) {
//                consoleStr1line.delete(0, consoleStr1line.length());
                //              consoleStr1line.append(ready4input ? onString : offString);
                //            if (isAuto) {
                //consoleStr1line.append("  ").append(DataBaseRepository.strAuto);
                //          }


                //        for (int i = 0; i < diceArr.length; i++) {
                //          if (diceArr[i] != 0) {
//                        consoleStr1line.append(i == 0 ? "-" : "").append(diceArr[i]);
//                    }
//                }
//                //first fill
                graphics.setColor(0, 0, 0);
                graphics.fillRect(0, 0, 240, 320);

                graphics.setColor(screen, screen, screen);

                makeConsoleString0();
                graphics.drawString(consoleStr1line.toString(), 0, 0, 0);

                makeConsoleString();
                graphics.drawString(consoleStr1line.toString(), 0, 17, 0);
                //              graphics.drawString(String.valueOf(count), 85, 0, 0);
//                if (isAuto) {
//                    graphics.drawString(String.valueOf("auto"), 119, 0, 0);
//                }
//                if (islight) {
//                    graphics.drawString(String.valueOf("light"), 150, 0, 0);
//                }
//                if (isBat) {
//                    graphics.drawString(String.valueOf("bat"), 160, 0, 0);
//                }
//                //the one of three
                makeOneHandString(dealer, strDealer);
                graphics.drawString(sbDealer.toString(), 0, 37, 0);

                makeOneHandString(playAa, strPlayAa);
                graphics.drawString(sbDealer.toString(), 0, 54, 0);
                // makeOneOtherString(threeString, doubleString, DataBaseRepository.anyThreeDiff, DataBaseRepository.anyDoubleDiff, DataBaseRepository.anyThree, DataBaseRepository.anyDouble);
                makeOneHandString(playBb, strPlayBb);
                graphics.drawString(sbDealer.toString(), 0, 71, 0);
                // makeOneOtherString(threeString, doubleString, DataBaseRepository.anyThreeDiff, DataBaseRepository.anyDoubleDiff, DataBaseRepository.anyThree, DataBaseRepository.anyDouble);
                makeOneHandString(playCc, strPlayCc);
                graphics.drawString(sbDealer.toString(), 0, 88, 0);
                // makeOneOtherString(threeString, doubleString, DataBaseRepository.anyThreeDiff, DataBaseRepository.anyDoubleDiff, DataBaseRepository.anyThree, DataBaseRepository.anyDouble);
                // graphics.drawString(displayString.toString(), 0, 55, 0);
                //end the three

                // makeSum6SingleString(3);
                graphics.drawString(displayString.toString(), 0, 76, 0);
                // makeSum6SingleNoShownString(3);
                graphics.drawString(displayString.toString(), 0, 94, 0);

                //  makeTheFourHapperString(4);
                graphics.drawString(displayString.toString(), 0, 115, 0);
                //  makeTheFourNoShownString(4);
                graphics.drawString(displayString.toString(), 0, 132, 0);

                // makeComboHappenString(3);
                graphics.drawString(displayString.toString(), 0, 153, 0);
                // makeComboHappenString2(3);
                graphics.drawString(displayString.toString(), 0, 170, 0);
                // makeComboHappenString3(3);
                graphics.drawString(displayString.toString(), 0, 187, 0);

                // makeDaHeHappenString(3);
                graphics.drawString(displayString.toString(), 0, 208, 0);
                // makeXiaoHeHappenString(3);
                graphics.drawString(displayString.toString(), 0, 225, 0);

                // makeTripleSingleString(3);
                graphics.drawString(displayString.toString(), 0, 246, 0);


                if (isBat) {
                    graphics.drawString(batString.toString(), 0, 266, 0);
                    graphics.drawString(batStringEvenOdd.toString(), 120, 266, 0);
                    //graphics.drawString("余额: " + DataBaseRepository.money, 0, 288, 0);
                }
            }

            protected void keyPressed(int keyCode) {
                if (keyCode == -7) {
                    islight = !islight;
                }
                if (keyCode == 57) {
                    isBat = !isBat;
                }
                if (keyCode == -1) { //up key light screen
                    if (islight) {
                        if (screen < 240) {
                            screen += 5;
                        }
                    }
                    if (isBat) {
                        batString.delete(0, batString.length());
                        activeIndexPlus++;
                        activeIndex = activeIndexPlus%4;
                        // batString.append("押大" + (++DataBaseRepository.amountBig));
                    }
                }
                if (keyCode == -2) { // down key dark screen
                    if (islight) {
                        if (screen > 45) {
                            screen -= 5;
                        }
                    }
                    if (isBat) {
                        batString.delete(0, batString.length());
                        //batString.append("押小" + (++DataBaseRepository.amountSml));
                    }
                }

                if (keyCode == -3) { //left key light screen
                    if (isBat) {
                        batStringEvenOdd.delete(0, batString.length());
                        //batStringEvenOdd.append("押双" + (++DataBaseRepository.amountEven));
                    }
                }
                if (keyCode == -4) { // right key dark screen
                    if (isBat) {
                        batStringEvenOdd.delete(0, batString.length());
                        //batStringEvenOdd.append("押单" + (++DataBaseRepository.amountOdd));
                    }
                }

                if (keyCode == 55) {
                    //  isAuto = !isAuto;
                }

                if (keyCode == 42) { //boom start
                    shuffleCard();
                    initDeal();
                }

                if(keyCode==49){
                    if(activeIndex==1){
                        for(int j =1;j<12;j++){
                           if(playAa[j]==0){
                               playAa[j]= getOneCardFromDeck();
                               break;
                           }
                        }
                    }

                    if(activeIndex==2){
                        for(int j =1;j<12;j++){
                           if(playBb[j]==0){
                               playBb[j]= getOneCardFromDeck();
                               break;
                           }
                        }
                    }

                    if(activeIndex==3){
                        for(int j =1;j<12;j++){
                           if(playCc[j]==0){
                               playCc[j]= getOneCardFromDeck();
                               break;
                           }
                        }
                    }

                    if(activeIndex==0){
                        for(int j =1;j<12;j++){
                           if(dealer[j]==0){
                               dealer[j]= getOneCardFromDeck();
                               break;
                           }
                        }
                    }




                }

                if (keyCode >= 49 && keyCode <= 54 && 1==2) {
                    if (ready4input) {
                        if (index < 3) {
                            diceArr[index++] = keyCode - 48;
                        } else {
                           // transfer(diceArr, isBat);
                        }
                    }
                }

                if (keyCode == 35) {//#
                  if(deck.size()<20){
                      return;
                  }
                    initDeal();
                }
                if (keyCode == 48) {//0
                    shuffleCard();
                }
                if (keyCode == 56) { // 8 key
                    batString.delete(0, batString.length());
                    batStringEvenOdd.delete(0, batStringEvenOdd.length());

                  
                }
                repaint();
            }

            protected void keyRepeated(int keyCode) {
                if (keyCode == -1) { //up key light screen
                    if (islight) {
                        if (screen < 240) {
                            screen += 5;
                        }
                    }
                    if (isBat) {
                        batString.delete(0, batString.length());
                        //batString.append("押大" + (++DataBaseRepository.amountBig));
                    }
                }
                if (keyCode == -2) { // down key dark screen
                    if (islight) {
                        if (screen > 45) {
                            screen -= 5;
                        }
                    }
                    if (isBat) {
                        batString.delete(0, batString.length());
                        //batString.append("押小" + (++DataBaseRepository.amountSml));
                    }
                }
                if (keyCode == 56) { // 8 key
                    batString.delete(0, batString.length());
                    batStringEvenOdd.delete(0, batStringEvenOdd.length());

                  
                }
                repaint();
            }

        

        
            private void clearEverything() {
                consoleStr1line.delete(0, consoleStr1line.length());
                displayString.delete(0, displayString.length());
                ready4input = false;
                //isAuto = false;
                for (int i = 0; i < diceArr.length; i++) {
                    diceArr[i] = 0;
                }
                index = 0;
                count = 0;
                //ServiceHandle.clearDataBase();
            }

//            //useful
//            private void makeSum6SingleNoShownString(int digit) {
//                displayString.delete(0, displayString.length());
//                displayString.append(wuChu).append(emptyString).
//                        append(charSep).append(formatIntToString(DataBaseRepository.intSingle6ns, digit)).
//                        append(charSep).append(formatIntToString(DataBaseRepository.intSingle5ns, digit)).
//                        append(charSep).append(formatIntToString(DataBaseRepository.intSingle4ns, digit)).
//                        append(charSep).append(formatIntToString(DataBaseRepository.intSingle3ns, digit)).
//                        append(charSep).append(formatIntToString(DataBaseRepository.intSingle2ns, digit)).
//                        append(charSep).append(formatIntToString(DataBaseRepository.intSingle1ns, digit)).
//                        append(charSep);
//            }
        

           

            private void shuffleCard() {
                int[] card1 = new int[54 * 6];
                int[] result = new int[54 * 6];
                for (int i = 0; i < card1.length; i++) {
                    card1[i] = i + 1;
                }
//		// this is for console show, comment it
//		for (int i = 0; i < card1.length; i++) {
//			System.out.print(card1[i] + "\t");
//			if ((i + 1) % 10 == 0)
//				System.out.println();
//		}

                // Random rand = new Random(System.currentTimeMillis());
                Random rand = new Random();

                int rand_index = 0;
                int remain = card1.length;

                for (int i = 0; i < card1.length; i++) {
                    rand_index = Math.abs(rand.nextInt() % remain);
                    result[i] = card1[rand_index];
                    card1[rand_index] = card1[remain - 1];
                    remain--;
                }

                //System.out.println("\n\n");
                for (int i = 0; i < result.length; i++) {
                    System.out.print(result[i] + "\t");
                    if ((i + 1) % 10 == 0) {
                        System.out.println();
                    }
                    deck.addElement(new Integer(result[i]));
                }




                //System.out.println("over");

                //  for(int i=0;i<deck.size();i++){
                //    System.out.println(deck.elementAt(i));
                //deck.removeElementAt(0);
                // System.out.println("deck.size():  "+deck.size());
                //   }
                //throw new UnsupportedOperationException("Not yet implemented");
            }

            private void initDeal() {
                playAa[1] = getOneCardFromDeck();
                playBb[1] = getOneCardFromDeck();
                playCc[1] = getOneCardFromDeck();
                dealer[1] = getOneCardFromDeck();
                playAa[2] = getOneCardFromDeck();
                playBb[2] = getOneCardFromDeck();
                playCc[2] = getOneCardFromDeck();

                //throw new UnsupportedOperationException("Not yet implemented");
            }

            private int getOneCardFromDeck() {
                int i = Integer.parseInt(deck.elementAt(deckIndex++).toString());
                int result = i % 13;
                return result == 0 ? 13 : result;
                //throw new UnsupportedOperationException("Not yet implemented");
            }

            private void makeOneHandString(int[] dealerArg, String name) {
                sbDealer.delete(0, sbDealer.length());
                int i = calcSums(dealerArg);
                //  formatArrayToString(dealerArg);
                sbDealer.append(name).append(": [").append(i).append("]  ");
                for (int j = 1; j < dealerArg.length; j++) {
                    if (dealerArg[j] != 0) {

                        if (dealerArg[j] < 11) {
                            sbDealer.append("[" + dealerArg[j] + "]");
                        } else if (dealerArg[j] == 11) {
                            sbDealer.append("[J]");
                        } else if (dealerArg[j] == 12) {
                            sbDealer.append("[Q]");
                        } else if (dealerArg[j] == 13) {
                            sbDealer.append("[K]");
                        } else if (dealerArg[j] == 0) {
                            //sbDealer.append("[K]");
                        } else {
                            System.out.println("Never disp!");
                        }
                    }
                }

                // calcSums(dealer);

                //throw new UnsupportedOperationException("Not yet implemented");
            }

            private int calcSums(int[] yiTuoPai) {
                tmpSumHolder = 0;
                yiTuoPai[0] = 0;
                for (int i = 1; i < yiTuoPai.length; i++) {
                    if (yiTuoPai[i] == 0) {
                        continue;
                    }
                    switch (yiTuoPai[i]) {
                        case 1:
                            if (tmpSumHolder > 10) {
                                tmpSumHolder += 1;
                            } else {
                                tmpSumHolder += 11;
                                yiTuoPai[0] = yiTuoPai[0] + 1;
                            }
                            break;
                        case 2:
                            tmpSumHolder += 2;
                            break;
                        case 3:
                            tmpSumHolder += 3;
                            break;
                        case 4:
                            tmpSumHolder += 4;
                            break;
                        case 5:
                            tmpSumHolder += 5;
                            break;
                        case 6:
                            tmpSumHolder += 6;
                            break;
                        case 7:
                            tmpSumHolder += 7;
                            break;
                        case 8:
                            tmpSumHolder += 8;
                            break;
                        case 9:
                            tmpSumHolder += 9;
                            break;
                        case 10:
                            tmpSumHolder += 10;
                            break;
                        case 11:
                            tmpSumHolder += 10;
                            break;
                        case 12:
                            tmpSumHolder += 10;
                            break;
                        case 13:
                            tmpSumHolder += 10;
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Never Display 016!");
                            break;
                    }// end switch
                }// end for loop
                while (tmpSumHolder > 21) {
                    if (yiTuoPai[0] == 0) {
                        break;
                    } else {
                        tmpSumHolder -= 10;
                        yiTuoPai[0] = yiTuoPai[0] - 1;
                    }
                }// end while
                return tmpSumHolder;
            }

            private void formatArrayToString(int[] arr) {
                for (int i = 0; i < arr.length; i++) {
                }


                //throw new UnsupportedOperationException("Not yet implemented");
            }

            private void makeConsoleString0() {
                consoleStr1line.delete(0, consoleStr1line.length());
                consoleStr1line.append("1 要;2 停;3 分;4 服;5 双");

                //throw new UnsupportedOperationException("Not yet implemented");
            }
            private void makeConsoleString() {
                consoleStr1line.delete(0, consoleStr1line.length());
                consoleStr1line.append("次: ").append(count);
                consoleStr1line.append("     当前玩家: ");
                if(activeIndex == 0){
                    consoleStr1line.append(strDealer);
                }else if(activeIndex == 1){
                     consoleStr1line.append(strPlayAa);
                }else if(activeIndex == 2){
                     consoleStr1line.append(strPlayBb);
                }else if(activeIndex == 3){
                     consoleStr1line.append(strPlayCc);
                }else{
                    System.out.println("Never Disp!");
                }

                //throw new UnsupportedOperationException("Not yet implemented");
            }

        }; // end of anonymous class

        exit = new Command("Exit", Command.STOP, 1);
        canvas.addCommand(exit);
        canvas.setCommandListener(new CommandListener() {

            private String sawTheCommandString = "Saw the command: ";

            public void commandAction(Command c, Displayable d) {
                if (c == exit) {
                    notifyDestroyed();
                } else {
                    //System.out.println("Saw the command: " + c);
                    consoleStr1line.delete(0, consoleStr1line.length());
                    consoleStr1line.append(sawTheCommandString).append(c);
                }
            }
        });
        display.setCurrent(canvas);
    }
}
/*
protected void keyReleased(int keyCode) {
if (keyCode > 0) {
System.out.println("keyReleased " + ((char) keyCode));
} else {
System.out.println("keyReleased action " + getGameAction(keyCode));
}
}
 */
