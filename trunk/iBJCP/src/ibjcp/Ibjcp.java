package ibjcp;

import java.io.IOException;
import java.util.Random;
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
 * hu.bin@msn.com
 */
public class Ibjcp extends MIDlet {

    private Display display;
    private Command exit;
    private int[] dealer = new int[12];
    private int[] playAa = new int[12];
    private int[] playBb = new int[12];
    private int[] playCc = new int[12];
    private int[] bufferArr = new int[12];
    //
    private String strExit = "Exit";
    private String sawTheCommand = "Saw the command";
    private StringBuffer consoleStr1line = new StringBuffer("");
    private int screen = 0;
    private String appPausedString = "App paused.";

    // the constructor
    public Ibjcp() {
    }

    // 1 to destroy application
    public void destroyApp(boolean unconditional) {
    }

    // 2 to pause application
    public void pauseApp() {
        consoleStr1line.delete(0, consoleStr1line.length());
        consoleStr1line.append(appPausedString);
    }

    // 3 to start application
    public void startApp() {
        display = Display.getDisplay(this);

        Canvas canvas = new Canvas() { // anonymous class

            private String canEditMark = "*";
            private String errorForNeverOccur = "Error occurs in calcBjSumsForIntArray(int[] yiTuoPai)";
            private String endLoadImg = "end loading img...";
            private String logInfoPrefix = "==**log info**== ";
            private String strDealer = "庄";
            private String strPlayAa = "A";
            private String strPlayBb = "B";
            private String strPlayCc = "C";
            private String strBufferArr = "放";
            private String strHit = "要";
            private String strSep = "|";
            //
            private boolean isProduction = true;
            private boolean isImgLoaded = false;
            private boolean isDownCardShow = false;
            private boolean isRsShow = false;
            private boolean isMoneyEditable = false;
            private boolean isOutCardShow = false;
            //
            private Image[] allDeckImgIntArr = new Image[54];
            private Image imageHolder;
            //
            private int[] deckOne2Six = new int[52 * 6];
            private int[] levelGoodArr = new int[13];
            private int[] levelCommArr = new int[13];
            private int[] levelBadArr = new int[13];
            private int[] outCardsArr = new int[13];
            //
            private int tmpIntHolder;
            private int deckOne2SixIdx = 0;
            private int activeModel = 1;
            private int activeAction = 1;
            private int money = 0;
            private int count = -1;
            //
            private StringBuffer displayString = new StringBuffer("");
            private StringBuffer commSb = new StringBuffer("");
            private String errorStr;

            //this is the override method
            public void paint(Graphics graphics) {
                //make sure the img are imported
                if (!isImgLoaded) {
                    loadImages();
                    logInfo(endLoadImg);
                }
                //set the backgroud
                graphics.setColor(0, 128, 0);
                graphics.fillRect(0, 0, this.getWidth(), this.getHeight());

                //set the output string color
                graphics.setColor(screen, screen, screen);

                //make some console string
                makeConsoleString();
                graphics.drawString(displayString.toString(), 0, 0, 0);

                //draw dealer
                int j = 0;
                tmpIntHolder = calcBjSumsForIntArray(dealer);
                graphics.drawString(String.valueOf(tmpIntHolder), 120, 0, 0);
                for (int i = 1; i < 12; i++) {
                    if (dealer[i] != 0) {
                        graphics.drawImage(allDeckImgIntArr[dealer[i]], 220 - (j++) * 15, 0, 0);
                    }
                }
                //draw dealer over...
                drawOnePlayer(graphics, playAa, 220, 30, true);
                drawOnePlayer(graphics, playBb, 140, 30, true);
                drawOnePlayer(graphics, playCc, 60, 30, true);
                drawOnePlayer(graphics, bufferArr, 220, 70, false);

                if (isRsShow) {
                    makePossibleRs();
                    graphics.drawString("1", 0, 85, 0);
                    graphics.drawString("2", 0, 110, 0);
                    graphics.drawString("3", 0, 135, 0);

                    drawPossibleRs(graphics, levelGoodArr, 85);
                    drawPossibleRs(graphics, levelCommArr, 110);
                    drawPossibleRs(graphics, levelBadArr, 135);
                }

                if (isDownCardShow) {
                    parseDownCards();
                    int tmpIndex = 0;
                    tmpIndex = deckOne2SixIdx;
                    if (tmpIndex > 5 * 52) {
                        shuffleCard();
                    }
                    graphics.drawString(commSb.toString(), 21, 204, 0);
                    for (int i = 0; i < 52; i++) {
                        int tmpInt = deckOne2Six[tmpIndex++];
                        tmpInt = tmpInt % 52 == 0 ? 52 : tmpInt % 52;
                        graphics.drawImage(allDeckImgIntArr[tmpInt], 3 + (i % 13) * 18, 228 + (i / 13) * 22, 0);
                    }
                }

                if (isOutCardShow) {
                    int leftOffSet = 90;
                    StringBuffer sb = new StringBuffer();
                    clearIntArray(outCardsArr);
                    for (int i = 0; i < deckOne2SixIdx; i++) {
                        int tmpInt = deckOne2Six[i];
                        tmpInt = tmpInt % 13 == 0 ? 13 : tmpInt % 13;
                        outCardsArr[tmpInt - 1]++;
                    }
                    sb.delete(0, sb.length());
                    sb.append(outCardsArr[0]);
                    graphics.drawString(sb.toString(), leftOffSet, 110, 0);
                    sb.delete(0, sb.length());
                    for (int i = 0; i < 4; i++) {
                        sb.append(outCardsArr[i + 1]);
                        sb.append(" ");
                    }
                    graphics.drawString(sb.toString(), leftOffSet, 130, 0);
                    sb.delete(0, sb.length());
                    for (int i = 0; i < 4; i++) {
                        sb.append(outCardsArr[i + 5]);
                        sb.append(" ");
                    }
                    graphics.drawString(sb.toString(), leftOffSet, 150, 0);
                    sb.delete(0, sb.length());
                    for (int i = 0; i < 4; i++) {
                        sb.append(outCardsArr[i + 9]);
                        sb.append(" ");
                    }
                    graphics.drawString(sb.toString(), leftOffSet, 170, 0);
                }
            }//end paint method

            protected void keyPressed(int keyCode) {
                if (keyCode == -7) { //right key, the left key is exit! //chagne 7 when deploy
                    isMoneyEditable = !isMoneyEditable;
                }
                if (keyCode == -1) { //key up
                }
                if (keyCode == -2) { //key down
                }
                if (keyCode == -3) { //key left
                }
                if (keyCode == -4) { //key right
                }
                if (keyCode == 35) { //#
                    isRsShow = !isRsShow;
                }
                if (keyCode == 42) { //*
                    isDownCardShow = !isDownCardShow;
                }
                if (keyCode == 48) { //0
                    if (!isImgLoaded) {
                        loadImages();
                    }
                    shuffleCard();
                }
                if (keyCode == 49) { //1
                    startOneRound();
                    count++;
                }
                if (keyCode == 50) { //2
                    if (isMoneyEditable) {
                        money++;
                    }
                }
                if (keyCode == 51) { //3
                    isOutCardShow = !isOutCardShow;
                }
                if (keyCode == 52) { //4
                    if (activeModel == 5) {
                        return;
                    } else {
                        activeModel++;
                    }
                }
                if (keyCode == 53) { //5 ok
//                    loadImages();
//                    shuffleCard();
                    hitCard();
                }
                if (keyCode == 54) { //6
                    if (activeModel == 1) {
                        return;
                    } else {
                        activeModel--;
                    }
                }
                if (keyCode == 55) { //7
                }
                if (keyCode == 56) { //8
                    if (isMoneyEditable) {
                        money--;
                    }
                }
                if (keyCode == 57) { //9
                }
                repaint();
            }

            // hit method
            private void hitCard() {
                if (activeModel == 1 && activeAction == 1) {
                    for (int j = 1; j < 12; j++) {
                        if (playAa[j] == 0) {
                            playAa[j] = getOneCardFromDeck();
                            break;
                        }
                    }
                }
                if (activeModel == 2 && activeAction == 1) {
                    for (int j = 1; j < 12; j++) {
                        if (playBb[j] == 0) {
                            playBb[j] = getOneCardFromDeck();
                            break;
                        }
                    }
                }
                if (activeModel == 3 && activeAction == 1) {
                    for (int j = 1; j < 12; j++) {
                        if (playCc[j] == 0) {
                            playCc[j] = getOneCardFromDeck();
                            break;
                        }
                    }
                }
                if (activeModel == 4 && activeAction == 1) {
                    for (int j = 1; j < 12; j++) {
                        if (dealer[j] == 0) {
                            dealer[j] = getOneCardFromDeck();
                            break;
                        }
                    }
                }

                if (activeModel == 5 && activeAction == 1) {
                    for (int j = 1; j < 12; j++) {
                        if (bufferArr[j] == 0) {
                            bufferArr[j] = getOneCardFromDeck();
                            break;
                        }
                    }
                }
            }

            //this must be run at first, and only run once!
            public void loadImages() {
                ImageUtil imageUtil = new ImageUtil();

                Image tmpImage;
                if (isImgLoaded) {
                    return;
                }
                try {
                    for (int i = 1; i < allDeckImgIntArr.length; i++) {
                        tmpImage = Image.createImage("/" + (i) + ".PNG");
                        imageHolder = imageUtil.resizeImage(tmpImage, 18, 24);
                        allDeckImgIntArr[i] = imageHolder;
                    }
                    isImgLoaded = true;
                } catch (IOException ex) {
                    errorStr = ex.getMessage();
                    logInfo(errorStr);
                    isImgLoaded = false;
                }
            }

            //this must be run at first
            private void shuffleCard() {
                int[] sortedCards = new int[52 * 6];
                int[] randomCards = new int[52 * 6];
                for (int i = 0; i < sortedCards.length; i++) {
                    sortedCards[i] = i + 1;
                }
                // this is for console show, comment it
                /*
                for (int i = 0; i < sortedCards.length; i++) {
                outPutMsg(sortedCards[i] + "\t", false);
                if ((i + 1) % 10 == 0) {
                outPutMsg("", true);
                }
                }
                 *
                 */

                // Random rand = new Random(System.currentTimeMillis());
                Random random = new Random();
                int shuffleTimes = 0;
                shuffleTimes = Math.abs(random.nextInt() % 3) + 1;
                //System.out.println(shuffleTimes);
                for (int ii = 0; ii < shuffleTimes; ii++) {
                    //System.out.println("wash one");
                    int randomIndex = 0;
                    int remainCardsCount = sortedCards.length;
                    for (int i = 0; i < sortedCards.length; i++) {
                        randomIndex = Math.abs(random.nextInt() % remainCardsCount);
                        randomCards[i] = sortedCards[randomIndex];
                        sortedCards[randomIndex] = sortedCards[remainCardsCount - 1];
                        remainCardsCount--;
                    }
                    copyIntArray(randomCards, sortedCards);
                }
                /*
                for (int i = 0; i < 3; i++) {
                outPutMsg("", true);
                }
                 */
                // this is for console show, comment it
                /*
                for (int i = 0; i < randomCards.length; i++) {
                outPutMsg(randomCards[i] % 13 + "\t", false);
                if ((i + 1) % 10 == 0) {
                outPutMsg("", true);
                }
                }
                 */
                clearIntArray(deckOne2Six);
                copyIntArray(randomCards, deckOne2Six);
                deckOne2SixIdx = 0;
            }

            //presss 1 start one round
            private void startOneRound() {
                clearIntArray(playAa);
                clearIntArray(playBb);
                clearIntArray(playCc);
                clearIntArray(dealer);
                clearIntArray(bufferArr);
                playAa[1] = getOneCardFromDeck();
                playBb[1] = getOneCardFromDeck();
                playCc[1] = getOneCardFromDeck();
                dealer[1] = getOneCardFromDeck();
                playAa[2] = getOneCardFromDeck();
                playBb[2] = getOneCardFromDeck();
                playCc[2] = getOneCardFromDeck();
            }

            //hit will use method
            private int getOneCardFromDeck() {
                if (deckOne2SixIdx > 250) { //cards will out
                    shuffleCard();
                }
                int result = deckOne2Six[deckOne2SixIdx++] % 52;
                return result == 0 ? 52 : result;
            }

            //ok
            private void makeConsoleString() {
                displayString.delete(0, displayString.length());
                if (activeModel == 4) {
                    displayString.append(strDealer);
                } else if (activeModel == 1) {
                    displayString.append(strPlayAa);
                } else if (activeModel == 2) {
                    displayString.append(strPlayBb);
                } else if (activeModel == 3) {
                    displayString.append(strPlayCc);
                } else if (activeModel == 5) {
                    displayString.append(strBufferArr);
                } else {
                    logInfo(errorForNeverOccur);
                }
                displayString.append(strSep);
                if (activeAction == 1) {
                    displayString.append(strHit);
                }
                displayString.append(strSep);
                displayString.append(count);
                displayString.append(strSep);
                displayString.append(money);
                if (isMoneyEditable) {
                    displayString.append(canEditMark);
                }

            }

            //draw one player
            private void drawOnePlayer(Graphics graphics, int[] onePlayer, int xxWidth, int lineIndex, boolean flag) {
                int tmp = calcBjSumsForIntArray(onePlayer);
                if (flag) {
                    graphics.drawString(String.valueOf(tmp), xxWidth, 55, 0);
                }
                int j = 0;
                for (int i = 1; i < 12; i++) {
                    if (onePlayer[i] != 0) {
                        //logInfo(xxWidth + "xxWidth" + onePlayer[i]);
                        graphics.drawImage(allDeckImgIntArr[onePlayer[i]], xxWidth - (j++) * 15, 0 + lineIndex, 0);
                    }
                }
            }

            private void makePossibleRs() {
                clearIntArray(levelBadArr);
                clearIntArray(levelCommArr);
                clearIntArray(levelGoodArr);
                int[] tmpArr1 = new int[12];
                if (activeModel == 4) {
                    copyIntArray(dealer, tmpArr1);
                } else if (activeModel == 1) {
                    copyIntArray(playAa, tmpArr1);
                } else if (activeModel == 2) {
                    copyIntArray(playBb, tmpArr1);
                } else if (activeModel == 3) {
                    copyIntArray(playCc, tmpArr1);
                } else {
                    copyIntArray(dealer, tmpArr1);
                }

                int[] tmpArr = new int[12];

                for (int i = 1; i < 14; i++) {
                    clearIntArray(tmpArr);
                    copyIntArray(tmpArr1, tmpArr);
                    for (int j = 1; j < tmpArr.length; j++) {
                        if (tmpArr[j] == 0) {
                            tmpArr[j] = i;
                            break;
                        }//end if
                    }//end for

                    int rs = calcBjSumsForIntArray(tmpArr);
                    if (rs >= 17 && rs < 22) {
                        for (int i2 = 0; i2 < levelGoodArr.length; i2++) {
                            if (levelGoodArr[i2] == 0) {
                                levelGoodArr[i2] = i;
                                break;
                            }
                        }
                    } else if (rs < 17) {
                        for (int i2 = 0; i2 < levelGoodArr.length; i2++) {
                            if (levelCommArr[i2] == 0) {
                                levelCommArr[i2] = i;
                                break;
                            }
                        }
                    } else {
                        for (int i2 = 0; i2 < levelGoodArr.length; i2++) {
                            if (levelBadArr[i2] == 0) {
                                levelBadArr[i2] = i;
                                break;
                            }
                        }
                    }

                }//end out for
            }

            // utils ==================
            private void copyIntArray(int[] arrSrc, int[] arrTar) {
                System.arraycopy(arrSrc, 0, arrTar, 0, arrSrc.length);
            }
            // utils

            private void clearIntArray(int[] arrayArg) {
                for (int i = 0; i < arrayArg.length; i++) {
                    arrayArg[i] = 0;
                }
            }
            // utils

            private void logInfo(String mainMessage) {
                if (!isProduction) {
                    System.out.println(logInfoPrefix + mainMessage);
                }
            }
            // utils

            private void outPutMsg(String mainMessage, boolean isReturn) {
                if (!isProduction) {
                    if (isReturn) {
                        System.out.println(mainMessage);
                    } else {
                        System.out.print(mainMessage);
                    }
                }
            }
            // utils

            private int calcBjSumsForIntArray(int[] yiTuoPai) {
                int tmpSumHolder = 0;
                int[] cloneSrcArr = new int[12];
                copyIntArray(yiTuoPai, cloneSrcArr);
                int[] tmpArray = new int[12];
                //handle for 52 cards
                for (int i = 0; i < yiTuoPai.length; i++) {
                    if (yiTuoPai[i] == 0) {
                        //copy 0
                        tmpArray[i] = 0;
                    } else {
                        if (yiTuoPai[i] % 13 != 0) {
                            tmpArray[i] = yiTuoPai[i] % 13;
                        } else {
                            tmpArray[i] = 13;
                        }
                    }
                }
                clearIntArray(yiTuoPai);
                copyIntArray(tmpArray, yiTuoPai);
                clearIntArray(tmpArray);
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
                            logInfo(errorForNeverOccur);
                            break;
                        default:
                            logInfo(errorForNeverOccur);
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
                copyIntArray(cloneSrcArr, yiTuoPai);
                return tmpSumHolder;
            }

            private void drawPossibleRs(Graphics graphics, int[] rsArr, int height) {
                int j = 0;
                for (int i = 0; i < rsArr.length; i++) {
                    if (rsArr[i] != 0) {
                        graphics.drawImage(allDeckImgIntArr[rsArr[i]], 15 + (j++) * 15, height, 0);
                    }
                }
            }

            private void parseDownCards() {
                int tmpIndex = 0;
                int[] parseArray = new int[14];
                tmpIndex = deckOne2SixIdx;
                if (tmpIndex > 5 * 52) {
                    shuffleCard();
                }
                for (int i = 0; i < 52; i++) {
                    int tmpInt = deckOne2Six[tmpIndex++];
                    tmpInt %= 13;
                    parseArray[tmpInt]++;
                }
                commSb.delete(0, commSb.length());
                for (int i = 1; i < 13; i++) {
                    commSb.append(parseArray[i]);
                    if (i == 1 || i == 9) {
                        commSb.append("-");
                    } else {
                        commSb.append(" ");
                    }
                }
                commSb.append(parseArray[0]);
            }
            // end utils ==================
        }; // end of anonymous class 

        exit = new Command(strExit, Command.STOP, 1);
        canvas.addCommand(exit);
        canvas.setCommandListener(new CommandListener() {

            public void commandAction(Command c, Displayable d) {
                if (c == exit) {
                    notifyDestroyed();
                } else {
                    consoleStr1line.delete(0, consoleStr1line.length());
                    consoleStr1line.append(sawTheCommand).append(c);
                }
            }
        });
        display.setCurrent(canvas);
    }
}
