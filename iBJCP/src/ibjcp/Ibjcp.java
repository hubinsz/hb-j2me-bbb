package ibjcp;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.*;

/**
 * @author hubin
 */
public class Ibjcp extends MIDlet {

    private Display display;
    private Command exit;
    private int[] dealer = new int[12];
    private int[] playAa = new int[12];
    private int[] playBb = new int[12];
    private int[] playCc = new int[12];
    private int[] bufferArr = new int[12];
    private String strDealer = "庄家";
    private String strPlayAa = "玩A";
    private String strPlayBb = "玩B";
    private String strPlayCc = "玩C";
    private int count = 0;
    private Vector deck = new Vector();
    private int deckIndex = 0;
    private StringBuffer sbDealer = new StringBuffer("hello, java!");
    private int activeIndex = 0;
    //private int activeIndexPlus = 1;
    private boolean isBat = true;
    private StringBuffer consoleStr1line = new StringBuffer("");
    private int money = 0;
    private int tmp = 0;
    private StringBuffer sbRemain1 = new StringBuffer("");
    private StringBuffer sbRemain2 = new StringBuffer("");
    private boolean isRemainShow = false;
    private StringBuffer RsGood = new StringBuffer("");
    private StringBuffer RsSafe = new StringBuffer("");
    private StringBuffer RsBad = new StringBuffer("");
    //no use
    private StringBuffer displayString = new StringBuffer("");
    private StringBuffer batStringEvenOdd = new StringBuffer("");
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

    public Ibjcp() {
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
            private Image[] imgVector = new Image[54];
            private Image[] imgVector2 = new Image[54];
            private Image[] imgVector3 = new Image[54];
            private Image[] imgVector4 = new Image[54];
            private boolean loadImgFlag = false;
            private Image imageHolder;

            public void loadImages() {
                loadImgFlag = true;
                ImageUtil imageUtil = new ImageUtil();
                try {
                    Image tmpImage;
                    for (int i = 1; i < imgVector.length; i++) {
                        tmpImage = Image.createImage("/" + (i) + ".PNG");
                        imageHolder = imageUtil.effect_resizeImage(tmpImage, 12, 16);
                        imgVector[i] = imageHolder;
                        imageHolder = imageUtil.effect_resizeImage(tmpImage, 15, 20);
                        imgVector2[i] = imageHolder;
                        imageHolder = imageUtil.effect_resizeImage(tmpImage, 18, 24);
                        imgVector3[i] = imageHolder;
                        imageHolder = imageUtil.effect_resizeImage(tmpImage, 21, 28);
                        imgVector4[i] = imageHolder;
                        // System.out.println(imgVector.size());
                    }
                    // System.out.println(imgVector.size());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            public void paint(Graphics graphics) {


                if (!loadImgFlag) {
                    System.out.println("system load the img");
                    loadImages();
                }


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
                int lineBegin = 110;
                int line = 0;
                if (RsGood.length() > 30) {
                    String tmp = RsGood.toString();
                    graphics.drawString(tmp.substring(0, 24), 0, lineBegin + 17 * line++, 0);
                    graphics.drawString(tmp.substring(24, tmp.length()), 0, lineBegin + 17 * line++, 0);
                } else {
                    graphics.drawString(RsGood.toString(), 0, lineBegin + 17 * line++, 0);
                }
                //graphics.drawString(RsGood.toString(), 0, 105, 0);
                if (RsSafe.length() > 30) {
                    String tmp = RsSafe.toString();
                    graphics.drawString(tmp.substring(0, 24), 0, lineBegin + 17 * line++, 0);
                    graphics.drawString(tmp.substring(24, tmp.length()), 0, lineBegin + 17 * line++, 0);
                } else {
                    graphics.drawString(RsSafe.toString(), 0, lineBegin + 17 * line++, 0);
                }
                //graphics.drawString(RsGood.toString(), 0, 105, 0);
                if (RsBad.length() > 30) {
                    String tmp = RsBad.toString();
                    graphics.drawString(tmp.substring(0, 24), 0, lineBegin + 17 * line++, 0);
                    graphics.drawString(tmp.substring(24, tmp.length()), 0, lineBegin + 17 * line++, 0);
                } else {
                    graphics.drawString(RsBad.toString(), 0, lineBegin + 17 * line++, 0);
                }
                //graphics.drawString(RsGood.toString(), 0, 105, 0);
                // makeComboHappenString2(3);
                // graphics.drawString(RsSafe.toString(), 0, 122, 0);
                // makeComboHappenString3(3);
                //  graphics.drawString(RsBad.toString(), 0, 139, 0);

                // makeDaHeHappenString(3);
                graphics.drawString(displayString.toString(), 0, 208, 0);
                // makeXiaoHeHappenString(3);
                graphics.drawString(displayString.toString(), 0, 225, 0);

                // makeTripleSingleString(3);
                graphics.drawString(displayString.toString(), 0, 246, 0);

                //   tiledLayer.getCell(1, 1);

                //graphics.drawImage();

                if (isRemainShow) {
                    graphics.drawString(sbRemain1.toString(), 0, 255, 0);
                    graphics.drawString(sbRemain2.toString(), 0, 277, 0);
                    // graphics.drawString(batStringEvenOdd.toString(), 120, 266, 0);
                    //graphics.drawString("余额: " + DataBaseRepository.money, 0, 288, 0);
                }


              //  graphics.drawImage(imgVector[4], 5, 5,graphics.set);

                for(int i=0;i<13;i++){
                    graphics.drawImage(imgVector[(i+1)],i*12,110,0);
                }

                     for(int i=0;i<13;i++){
                    graphics.drawImage(imgVector2[(i+1)],i*15,130,0);
                }

                     for(int i=0;i<13;i++){
                    graphics.drawImage(imgVector3[(i+1)],i*18,160,0);
                }

                     for(int i=0;i<13;i++){
                    graphics.drawImage(imgVector4[(i+1)],i*21,198,0);
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
                        if (activeIndex > 0) {
                            activeIndex--;
                            makePossibleRs();
                        }
                        //makePossibleRs();
                        // batString.append("押大" + (++DataBaseRepository.amountBig));
                    }
                }
                if (keyCode == -2) { // down key dark screen
                    if (isBat) {
                        if (activeIndex < 3) {
                            activeIndex++;
                            makePossibleRs();
                        }
                        //makePossibleRs();
                        // batString.append("押大" + (++DataBaseRepository.amountBig));
                    }
                }

                if (keyCode == -3) { //left key light screen
                    if (isBat) {
                        //  batStringEvenOdd.delete(0, batString.length());
                        //batStringEvenOdd.append("押双" + (++DataBaseRepository.amountEven));
                    }
                }
                if (keyCode == -4) { // right key dark screen
                    if (isBat) {
                        //  batStringEvenOdd.delete(0, batString.length());
                        //batStringEvenOdd.append("押单" + (++DataBaseRepository.amountOdd));
                    }
                }

                if (keyCode == 55) {
                    //  isAuto = !isAuto;
                }

                if (keyCode == 42) { //*
                    if (deck.size() < 54) {
                        shuffleCard();
                    }
                    initDeal();
                }

                if (keyCode == 49) { //1 hit
                    tmp++;
                    System.out.println(tmp);
                    makeRemainString();
                    if (activeIndex == 1) {
                        for (int j = 1; j < 12; j++) {
                            if (playAa[j] == 0) {
                                playAa[j] = getOneCardFromDeck();
                                break;
                            }
                        }
                    }

                    if (activeIndex == 2) {
                        for (int j = 1; j < 12; j++) {
                            if (playBb[j] == 0) {
                                playBb[j] = getOneCardFromDeck();
                                break;
                            }
                        }
                    }

                    if (activeIndex == 3) {
                        for (int j = 1; j < 12; j++) {
                            if (playCc[j] == 0) {
                                playCc[j] = getOneCardFromDeck();
                                break;
                            }
                        }
                    }

                    if (activeIndex == 0) {
                        for (int j = 1; j < 12; j++) {
                            if (dealer[j] == 0) {
                                dealer[j] = getOneCardFromDeck();
                                break;
                            }
                        }
                    }
                    makePossibleRs();



                }

                if (keyCode >= 49 && keyCode <= 54 && 1 == 2) {
                }

                if (keyCode == 35) {//#
//                    if (deck.size() < 20) {
//                        return;
//                    }
//                    initDeal();
                }
                if (keyCode == 48) {//0
                    shuffleCard();
                    makeRemainString();
                }
                if (keyCode == 56) { // 8 key
                    isRemainShow = !isRemainShow;
                    makeRemainString();
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
                        // batString.delete(0, batString.length());
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
                        //  batString.delete(0, batString.length());
                        //batString.append("押小" + (++DataBaseRepository.amountSml));
                    }
                }
                if (keyCode == 56) { // 8 key
                    // batString.delete(0, batString.length());
                    batStringEvenOdd.delete(0, batStringEvenOdd.length());


                }
                repaint();
            }

            private void clearEverything() {
                consoleStr1line.delete(0, consoleStr1line.length());
                displayString.delete(0, displayString.length());

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
                deck.removeAllElements();
                deckIndex = 0;
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
                    //  System.out.print(result[i]%13 + "\t");
                    if ((i + 1) % 10 == 0) {
                        //   System.out.println();
                    }
                    deck.addElement(new Integer(result[i]));
                }

                deckIndex = 0;
                tmp = 0;


                //System.out.println("over");

                //  for(int i=0;i<deck.size();i++){
                //    System.out.println(deck.elementAt(i));
                //deck.removeElementAt(0);
                // System.out.println("deck.size():  "+deck.size());
                //   }
                //throw new UnsupportedOperationException("Not yet implemented");
            }

            private void initDeal() {

                clear(playAa);
                clear(playBb);
                clear(playCc);
                clear(dealer);

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

                        if (dealerArg[j] < 10 && dealerArg[j] != 1) {
                            sbDealer.append(" " + dealerArg[j] + " ");
                        } else if (dealerArg[j] == 11) {
                            sbDealer.append("[J]");
                        } else if (dealerArg[j] == 12) {
                            sbDealer.append("[Q]");
                        } else if (dealerArg[j] == 13) {
                            sbDealer.append("[K]");
                        } else if (dealerArg[j] == 0) {
                            //sbDealer.append("[K]");
                        } else if (dealerArg[j] == 1) {
                            sbDealer.append("[A]");
                        } else if (dealerArg[j] == 10) {
                            sbDealer.append("[10]");
                        } else {
                            System.out.println("Never disp!");
                        }
                    }//end if
                }//end for

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
                consoleStr1line.append("1 要;2 停;3 分;4 服;5 双;6 保险");
            }

            //OK
            private void makeConsoleString() {
                consoleStr1line.delete(0, consoleStr1line.length());
                consoleStr1line.append("次: ").append(count);
                consoleStr1line.append("     [");
                if (activeIndex == 0) {
                    consoleStr1line.append(strDealer);
                } else if (activeIndex == 1) {
                    consoleStr1line.append(strPlayAa);
                } else if (activeIndex == 2) {
                    consoleStr1line.append(strPlayBb);
                } else if (activeIndex == 3) {
                    consoleStr1line.append(strPlayCc);
                } else {
                    System.out.println("Never Disp!");
                }
                consoleStr1line.append("] ").append("钱:").append(money);
            }

            //OK
            private void clear(int[] arrayArg) {
                for (int i = 0; i < arrayArg.length; i++) {
                    arrayArg[i] = 0;
                }
            }

            private void makeRemainString() {

                sbRemain1.delete(0, sbRemain1.length());
                sbRemain2.delete(0, sbRemain2.length());
                for (int i = 0; i < 15; i++) {
                    int itmp = Integer.parseInt(deck.elementAt(i + tmp).toString());
                    itmp %= 13;

                    if (itmp < 10 && itmp != 1 && itmp != 0) {
                        sbRemain1.append("" + itmp + "");
                    } else if (itmp == 11) {
                        sbRemain1.append("[J]");
                    } else if (itmp == 12) {
                        sbRemain1.append("[Q]");
                    } else if (itmp == 13) {
                    } else if (itmp == 0) {
                        sbRemain1.append("[K]");
                    } else if (itmp == 1) {
                        sbRemain1.append("[A]");
                    } else if (itmp == 10) {
                        sbRemain1.append("[10]");
                    } else {
                        System.out.println("Never disp!");
                    }
                }

                for (int i = 15; i < 30; i++) {
                    int itmp = Integer.parseInt(deck.elementAt(i + tmp).toString());
                    itmp %= 13;

                    if (itmp < 10 && itmp != 1) {
                        sbRemain2.append("" + itmp + "");
                    } else if (itmp == 11) {
                        sbRemain2.append("[J]");
                    } else if (itmp == 12) {
                        sbRemain2.append("[Q]");
                    } else if (itmp == 13) {
                    } else if (itmp == 0) {
                        sbRemain2.append("[K]");
                    } else if (itmp == 1) {
                        sbRemain2.append("[A]");
                    } else if (itmp == 10) {
                        sbRemain2.append("[10]");
                    } else {
                        System.out.println("Never disp!");
                    }
                }


            }

            private void makePossibleRs() {
                clear(bufferArr);
                if (activeIndex == 0) {
                    copyArr(dealer, bufferArr);
                } else if (activeIndex == 1) {
                    copyArr(playAa, bufferArr);
                } else if (activeIndex == 2) {
                    copyArr(playBb, bufferArr);
                } else if (activeIndex == 3) {
                    copyArr(playCc, bufferArr);
                } else {
                    //
                }

                int[] tmpArr = new int[12];
                copyArr(bufferArr, tmpArr);

                RsGood.delete(0, RsGood.length());
                RsSafe.delete(0, RsSafe.length());
                RsBad.delete(0, RsBad.length());

                RsGood.append("好:");
                RsSafe.append("中:");
                RsBad.append("坏:");

                for (int i = 1; i < 14; i++) {
                    copyArr(tmpArr, bufferArr);
                    for (int j = 1; j < bufferArr.length; j++) {
                        if (bufferArr[j] == 0) {
                            bufferArr[j] = i;
                            break;
                        }//end if
                    }//end for

                    int rs = calcSums(bufferArr);
                    for (int ii = 0; ii < bufferArr.length; ii++) {
                        // System.out.println("hhhhhhh"+bufferArr[ii]);
                    }


                    if (rs > 17 && rs < 22) {
                        RsGood.append(i + "|" + rs + " ");
                    } else if (rs <= 17) {
                        RsSafe.append(i + "|" + rs + " ");
                    } else {
                        RsBad.append(i + "|" + rs + " ");
                    }

                }//end out for




            }

            private void copyArr(int[] arrSrc, int[] arrTar) {
                for (int i = 0; i < arrSrc.length; i++) {
                    arrTar[i] = arrSrc[i];
                }
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
