package Pages;

import javax.swing.*;
import java.awt.*;

public class PageTemplate extends JFrame {
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel subTitlePanel;
    private JLabel subTitleLabel;
    private JPanel iconsPanel;
    private JPanel creditsPanel;
    private JPanel creditsIconpanel;
    private JLabel creditsLabelPanel;
    private JPanel bodyPanel;
    private JPanel buttonsPanel;
    private JButton button1;
    private JButton button2;
    private JPanel contentPanel;
    private JButton icon2;
    private JButton icon1;

    public PageTemplate(){
        super();

        // general settings of JFrame
        this.setContentPane(this.mainPanel);
        this.setTitle(titleLabel.getText());
        this.setBounds(0,0,1000,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(JPanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(JLabel titleLabel) {
        this.titleLabel = titleLabel;
    }

    public JPanel getSubTitlePanel() {
        return subTitlePanel;
    }

    public void setSubTitlePanel(JPanel subTitlePanel) {
        this.subTitlePanel = subTitlePanel;
    }

    public JLabel getSubTitleLabel() {
        return subTitleLabel;
    }

    public void setSubTitleLabel(JLabel subTitleLabel) {
        this.subTitleLabel = subTitleLabel;
    }

    public JPanel getIconsPanel() {
        return iconsPanel;
    }

    public void setIconsPanel(JPanel iconsPanel) {
        this.iconsPanel = iconsPanel;
    }

    public JPanel getCreditsPanel() {
        return creditsPanel;
    }

    public void setCreditsPanel(JPanel creditsPanel) {
        this.creditsPanel = creditsPanel;
    }

    public JPanel getCreditsIconpanel() {
        return creditsIconpanel;
    }

    public void setCreditsIconpanel(JPanel creditsIconpanel) {
        this.creditsIconpanel = creditsIconpanel;
    }

    public JLabel getCreditsLabelPanel() {
        return creditsLabelPanel;
    }

    public void setCreditsLabelPanel(JLabel creditsLabelPanel) {
        this.creditsLabelPanel = creditsLabelPanel;
    }

    public JPanel getBodyPanel() {
        return bodyPanel;
    }

    public void setBodyPanel(JPanel bodyPanel) {
        this.bodyPanel = bodyPanel;
    }

    public JPanel getButtonsPanel() {
        return buttonsPanel;
    }

    public void setButtonsPanel(JPanel buttonsPanel) {
        this.buttonsPanel = buttonsPanel;
    }

    public JButton getButton1() {
        return button1;
    }

    public void setButton1(JButton button1) {
        this.button1 = button1;
    }

    public JButton getButton2() {
        return button2;
    }

    public void setButton2(JButton button2) {
        this.button2 = button2;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setContentPanel(JPanel contentPanel) {
        this.contentPanel = contentPanel;
    }

    public JButton getIcon2() {
        return icon2;
    }

    public void setIcon2(JButton icon2) {
        this.icon2 = icon2;
    }

    public JButton getIcon1() {
        return icon1;
    }

    public void setIcon1(JButton icon1) {
        this.icon1 = icon1;
    }
}
