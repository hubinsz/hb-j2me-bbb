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
    private Vector deck = new Vector();

    private int deckIndex = 1;


    private StringBuffer consoleStr1line = new StringBuffer("hello, java!");
    private StringBuffer displayString = new StringBuffer("hello, java!");
    private StringBuffer batString = new StringBuffer("");
    private StringBuffer batStringEvenOdd = new StringBuffer("");
    private boolean ready4input = false;
    private boolean isBat = false;
    private int[] diceArr = new int[3];
    private int index = 0;
    private int count = 0;
    private int screen = 170;
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

            private boolean isAuto = false;

            public void paint(Graphics graphics) {
                consoleStr1line.delete(0, consoleStr1line.length());
                consoleStr1line.append(ready4input ? onString : offString);
                if (isAuto) {
                    //consoleStr1line.append("  ").append(DataBaseRepository.strAuto);
                }


                for (int i = 0; i < diceArr.length; i++) {
                    if (diceArr[i] != 0) {
                        consoleStr1line.append(i == 0 ? "-" : "").append(diceArr[i]);
                    }
                }
                //first fill
                graphics.setColor(0, 0, 0);
                graphics.fillRect(0, 0, 240, 320);

                graphics.setColor(screen, screen, screen);
                // graphics.setColor(255, 255, 255);

                // graphics.drawLine(0, 15, 240, 15);
                // graphics.drawLine(0, 59, 240, 59);
                //graphics.drawLine(0, 89, 240, 89);
                // graphics.drawLine(0, 119, 240, 119);
                // graphics.drawLine(0, 164, 240, 164);
                // graphics.drawLine(0, 194, 240, 194);
                // makedispString();
                // if(showZero){
                //     tempStr = dispString.toString();
                // }else{
                //     tempStr = dispString.toString();
                //     tempStr = tempStr.replace('0', ' ');
                // }
                //console
                graphics.drawString(consoleStr1line.toString(), 0, 0, 0);
                graphics.drawString(String.valueOf(count), 85, 0, 0);
                if (isAuto) {
                    graphics.drawString(String.valueOf("auto"), 119, 0, 0);
                }
                if (islight) {
                    graphics.drawString(String.valueOf("light"), 150, 0, 0);
                }
                if (isBat) {
                    graphics.drawString(String.valueOf("bat"), 160, 0, 0);
                }
                //the one of three
                //  makeOneOtherString(bigString, smlString, DataBaseRepository.intBigDiff, DataBaseRepository.intSmallDiff, DataBaseRepository.intBig, DataBaseRepository.intSmall);
                graphics.drawString(displayString.toString(), 0, 21, 0);
                //  makeOneOtherString(evenString, oddString, DataBaseRepository.intEvenDiff, DataBaseRepository.intOddDiff, DataBaseRepository.intEven, DataBaseRepository.intOdd);
                graphics.drawString(displayString.toString(), 0, 38, 0);
                // makeOneOtherString(threeString, doubleString, DataBaseRepository.anyThreeDiff, DataBaseRepository.anyDoubleDiff, DataBaseRepository.anyThree, DataBaseRepository.anyDouble);
                graphics.drawString(displayString.toString(), 0, 55, 0);
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
                    isAuto = !isAuto;
                }

                if (keyCode == 42) { //boom start
                    ready4input = !ready4input;
                }

                if (keyCode >= 49 && keyCode <= 54) {
                    if (ready4input) {
                        if (index < 3) {
                            diceArr[index++] = keyCode - 48;
                        } else {
                            transfer(diceArr, isBat);
                        }
                    }
                }

                if (keyCode == 35) {//#
                    if (index == 3) {
                        transfer(diceArr, isBat);
                    }
                }
                if (keyCode == 48) {//0
                    shuffleCard();
                }
                if (keyCode == 56) { // 8 key
                    batString.delete(0, batString.length());
                    batStringEvenOdd.delete(0, batStringEvenOdd.length());

                    if (isAuto) {
                        diceArr[0] = getOneDice();
                        diceArr[1] = getOneDice();
                        diceArr[2] = getOneDice();
                        transfer(diceArr, isBat);
                    }
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

                    if (isAuto) {
                        diceArr[0] = getOneDice();
                        diceArr[1] = getOneDice();
                        diceArr[2] = getOneDice();
                        transfer(diceArr, isBat);
                    }
                }
                repaint();
            }

            private int getOneDice() {
                int tmp = random.nextInt();
                if (tmp >= 0) {
                    return tmp % 6 + 1;
                } else {
                    tmp = -tmp;
                    return tmp % 6 + 1;
                }
            }

            private void transfer(int[] diceArr, boolean isCalc) {
                count++;
                //ServiceHandle.getDiceArrAndCalc(diceArr, isCalc);

                index = 0;
                for (int i = 0; i < diceArr.length; i++) {
                    diceArr[i] = 0;
                }
            }

            private void clearEverything() {
                consoleStr1line.delete(0, consoleStr1line.length());
                displayString.delete(0, displayString.length());
                ready4input = false;
                isAuto = false;
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
            //useful
            private String formatIntToString(int argInt, int length) {
                if (argInt == 0) {
                    return "         ".substring(0, length);
                }
                String tmpStr = String.valueOf(argInt);
                if (tmpStr.length() == length) {
                    return tmpStr;
                } else if (tmpStr.length() > length) {
                    return tmpStr.substring(tmpStr.length() - length, tmpStr.length());
                } else {
                    while (tmpStr.length() < length) {
                        tmpStr = " " + tmpStr;
                    }
                    return tmpStr;
                }
            }

            //useful
            private void makeOneOtherString(String bigStringArg, String smlStringArg, int bigIntDiff, int smlIntDiff, int bigIntMore, int smlIntMore) {
                int tmpContinueCount = 0;
                int tmpMoreCount = 0;
                displayString.delete(0, displayString.length());
                // da lian 6
                if (bigIntDiff > smlIntDiff) {
                    tmpContinueCount = bigIntDiff - smlIntDiff;
                    displayString.append(leftBlock).append(bigStringArg).append(rightBlock).append(continueString).append(tmpContinueCount);
                } else if (bigIntDiff < smlIntDiff) {
                    tmpContinueCount = smlIntDiff - bigIntDiff;
                    displayString.append(leftBlock).append(smlStringArg).append(rightBlock).append(continueString).append(tmpContinueCount);
                } else {
                    tmpContinueCount = smlIntDiff - bigIntDiff;
                    displayString.append(emptyString).append(tieString).append(tmpContinueCount);
                }
                displayString.append(emptyString);
                // da duo 5
                if (bigIntMore > smlIntMore) {
                    tmpMoreCount = bigIntMore - smlIntMore;
                    displayString.append(leftBlock).append(bigStringArg).append(rightBlock).append(moreString).append(tmpMoreCount);
                } else if (bigIntMore < smlIntMore) {
                    tmpMoreCount = smlIntMore - bigIntMore;
                    displayString.append(leftBlock).append(smlStringArg).append(rightBlock).append(moreString).append(tmpMoreCount);
                } else {
                    tmpMoreCount = smlIntMore - bigIntMore;
                    displayString.append(emptyString).append(tieString).append(tmpMoreCount);
                }
                displayString.append(emptyString);
                // da ke ya [6][5]
                if (bigIntDiff > smlIntDiff && bigIntMore > smlIntMore) {
                    tmpContinueCount = bigIntDiff - smlIntDiff;
                    tmpMoreCount = bigIntMore - smlIntMore;
                    displayString.append(leftBlock).append(smlStringArg).append(rightBlock).append(canbetString).append(leftBlock).append(tmpContinueCount).append(rightBlock).append(leftBlock).append(tmpMoreCount).append(rightBlock);
                } else if (bigIntDiff < smlIntDiff && bigIntMore < smlIntMore) {
                    tmpContinueCount = smlIntDiff - bigIntDiff;
                    tmpMoreCount = smlIntMore - bigIntMore;
                    displayString.append(leftBlock).append(bigStringArg).append(rightBlock).append(canbetString).append(leftBlock).append(tmpContinueCount).append(rightBlock).append(leftBlock).append(tmpMoreCount).append(rightBlock);
                } else {
                    displayString.append(emptyString).append(canbetString).append(leftBlock).append(rightBlock).append(leftBlock).append(rightBlock);
                }
            }

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
                    //	System.out.print(result[i] + "\t");
                    //	if ((i + 1) % 10 == 0)
                    //		System.out.println();
                    deck.addElement(new Integer(result[i]));
                }




                //System.out.println("over");

                //   for(int i=0;i<deck.size();i++){
                //    System.out.println(deck.elementAt(i));
                //deck.removeElementAt(0);
                // System.out.println("deck.size():  "+deck.size());
                //   }
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
