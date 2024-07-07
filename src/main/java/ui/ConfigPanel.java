package ui;

import utils.UiUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;

public class ConfigPanel extends JPanel {
    public static JLabel lbRequestCount;
    public static JLabel lbSuccessCount;
    public static JLabel jsCrawledCount;
    public static JLabel urlCrawledCount;
    public static JComboBox<String> choicesComboBox;
    // 在FingerTab类中添加成员变量
    public static JToggleButton flashButton;
    public static JToggleButton toggleButton;
    public static JTextField searchField;
    public static JLabel flashText;
//    JCheckBox autoSendRequestCheckBox;
//    JCheckBox includeCookieCheckBox;

    public ConfigPanel() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        //GridBagLayout 允许以网格形式布局容器中的组件，同时为每个组件提供独立的定位和大小控制，非常适用于需要复杂布局设计的GUI界面。
        // 列数，行数  //表示容器被划分为两列，每一列的初始宽度均为0。
        // 这里的0不代表实际宽度为零，而是告诉布局管理器根据组件的实际大小和其他约束（如权重）来计算列宽。
        gridBagLayout.columnWidths = new int[] { 0, 0};
        gridBagLayout.rowHeights = new int[] {5};
        // 各列占宽度比，各行占高度比
        gridBagLayout.columnWeights = new double[] { 1.0D, Double.MIN_VALUE };
        //设置了两列的扩展权重。第一列的权重为1.0，意味着当容器有多余空间时，这一列会优先扩展以填充可用空间。
        // 第二列的权重设为Double.MIN_VALUE，表示这一列不应该扩展，保持最小或固定大小。
        setLayout(gridBagLayout);

        //创建FilterPanel
        JPanel FilterPanel = new JPanel();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.insets = new Insets(0, 5, 5, 5);
        gbc_panel_1.fill = 2;
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 2;
        add(FilterPanel, gbc_panel_1);
        //设置一个名为FilterPanel的面板在父容器中的布局位置
        // 布局约束包括：
        //insets: 设置了组件边缘的内边距，上5px，左5px，下5px，右5px，为组件提供一定的间距。
        //fill: 设置组件在可扩展空间中的填充方式，值为2表示BOTH，即组件可以在水平和垂直方向上填充其显示区域。
        //gridx 和 gridy: 分别设置组件在网格布局中的起始列和起始行，这里是第0列第2行。

        //为 FilterPanel 设置布局
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0 };  //设置每列的初始宽度为0 指示布局管理器根据组件实际大小和其他约束来计算宽度。
        gbl_panel_1.rowHeights = new int[] { 0, 0 };  //设置每行的初始高度为0，指按需计算行高。

        // 指定每列的扩展权重。这里前9列的权重都设为0.0，意味着这些列不会随容器大小变化而扩展，
        // 而最后列的权重设为Double.MIN_VALUE，这通常用于指示该列应该尽可能小，不参与额外空间的分配。
        gbl_panel_1.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, Double.MIN_VALUE};
        //第一行权重为0.0，不随容器扩展，第二行的权重为Double.MIN_VALUE，表示该行也不扩展。
        gbl_panel_1.rowWeights = new double[] { 0.0D, Double.MIN_VALUE };
        FilterPanel.setLayout(gbl_panel_1);

        // 在添加 "Requests Total" 和 lbRequestCount 之前添加一个占位组件
        Component leftStrut = Box.createHorizontalStrut(5); // 你可以根据需要调整这个值
        GridBagConstraints gbc_leftStrut = new GridBagConstraints();
        gbc_leftStrut.insets = new Insets(0, 0, 0, 5);
        gbc_leftStrut.fill = GridBagConstraints.HORIZONTAL;
        gbc_leftStrut.weightx = 1.0; // 这个值决定了 leftStrut 占据的空间大小
        gbc_leftStrut.gridx = 8;
        gbc_leftStrut.gridy = 0;
        FilterPanel.add(leftStrut, gbc_leftStrut);

        // 转发url总数，默认0
        JLabel lbRequest = new JLabel("Requests Total:");
        GridBagConstraints gbc_lbRequest = new GridBagConstraints();
        gbc_lbRequest.insets = new Insets(0, 0, 0, 5);
        gbc_lbRequest.fill = GridBagConstraints.HORIZONTAL;
        gbc_lbRequest.weightx = 0.0;
        gbc_lbRequest.gridx = 0;
        gbc_lbRequest.gridy = 0;
        FilterPanel.add(lbRequest, gbc_lbRequest);

        lbRequestCount = new JLabel("0");
        lbRequestCount.setForeground(new Color(0,0,255));
        GridBagConstraints gbc_lbRequestCount = new GridBagConstraints();
        gbc_lbRequestCount.insets = new Insets(0, 0, 0, 5);
        gbc_lbRequest.fill = GridBagConstraints.HORIZONTAL;
        gbc_lbRequest.weightx = 0.0;
        gbc_lbRequestCount.gridx = 1;
        gbc_lbRequestCount.gridy = 0;
        FilterPanel.add(lbRequestCount, gbc_lbRequestCount);

        // 转发成功url数，默认0
        JLabel lbSucces = new JLabel("Success Domain:");
        GridBagConstraints gbc_lbSucces = new GridBagConstraints();
        gbc_lbSucces.insets = new Insets(0, 0, 0, 5);
        gbc_lbSucces.fill = 0;
        gbc_lbSucces.gridx = 2;
        gbc_lbSucces.gridy = 0;
        FilterPanel.add(lbSucces, gbc_lbSucces);

        lbSuccessCount = new JLabel("0");
        lbSuccessCount.setForeground(new Color(0, 255, 0));
        GridBagConstraints gbc_lbSuccessCount = new GridBagConstraints();
        gbc_lbSuccessCount.insets = new Insets(0, 0, 0, 5);
        gbc_lbSuccessCount.fill = 0;
        gbc_lbSuccessCount.gridx = 3;
        gbc_lbSuccessCount.gridy = 0;
        FilterPanel.add(lbSuccessCount, gbc_lbSuccessCount);

        // 爬取JS的数量
        JLabel urlCrawled = new JLabel("Analyze Url:");
        GridBagConstraints gbc_urlCrawled = new GridBagConstraints();
        gbc_urlCrawled.insets = new Insets(0, 0, 0, 5);
        gbc_urlCrawled.fill = 0;
        gbc_urlCrawled.gridx = 4;
        gbc_urlCrawled.gridy = 0;
        FilterPanel.add(urlCrawled, gbc_urlCrawled);

        urlCrawledCount = new JLabel("0/0");
        urlCrawledCount.setForeground(new Color(0, 0, 255)); // 蓝色
        GridBagConstraints gbc_urlCrawledCount = new GridBagConstraints();
        gbc_urlCrawledCount.insets = new Insets(0, 0, 0, 5);
        gbc_urlCrawledCount.fill = 0;
        gbc_urlCrawledCount.gridx = 5;
        gbc_urlCrawledCount.gridy = 0;
        FilterPanel.add(urlCrawledCount, gbc_urlCrawledCount);


        // 爬取JS的数量
        JLabel jsCrawled = new JLabel("Crawled JS:");
        GridBagConstraints gbc_jsCrawled = new GridBagConstraints();
        gbc_jsCrawled.insets = new Insets(0, 0, 0, 5);
        gbc_jsCrawled.fill = 0;
        gbc_jsCrawled.gridx = 6;
        gbc_jsCrawled.gridy = 0;
        FilterPanel.add(jsCrawled, gbc_jsCrawled);

        jsCrawledCount = new JLabel("0/0");
        jsCrawledCount.setForeground(new Color(0, 0, 255)); // 蓝色
        GridBagConstraints gbc_jsCrawledCount = new GridBagConstraints();
        gbc_jsCrawledCount.insets = new Insets(0, 0, 0, 5);
        gbc_jsCrawledCount.fill = 0;
        gbc_jsCrawledCount.gridx = 7;
        gbc_jsCrawledCount.gridy = 0;
        FilterPanel.add(jsCrawledCount, gbc_jsCrawledCount);

        // 开关 是否开启对提取URL进行发起请求
        toggleButton = new JToggleButton(UiUtils.getImageIcon("/icon/openButtonIcon.png", 40, 24));
        toggleButton.setSelectedIcon(UiUtils.getImageIcon("/icon/shutdownButtonIcon.png", 40, 24));
        toggleButton.setPreferredSize(new Dimension(50, 24));
        toggleButton.setBorder(null);  // 设置无边框
        toggleButton.setFocusPainted(false);  // 移除焦点边框
        toggleButton.setContentAreaFilled(false);  // 移除选中状态下的背景填充
        toggleButton.setToolTipText("是否开启对提取URL进行发起请求");

        // 刷新按钮按钮
        flashButton = new JToggleButton(UiUtils.getImageIcon("/icon/runningButton.png", 24, 24));
        flashButton.setSelectedIcon(UiUtils.getImageIcon("/icon/flashButton.png", 24, 24));
        flashButton.setPreferredSize(new Dimension(30, 30));
        flashButton.setBorder(null);  // 设置无边框
        flashButton.setFocusPainted(false);  // 移除焦点边框
        flashButton.setContentAreaFilled(false);  // 移除选中状态下的背景填充
        flashButton.setToolTipText("用于控制表格是否自动化刷新，还是手工点击刷新");

        // 刷新文本
        flashText = new JLabel("自动每10秒刷新表格中");

        // 添加填充以在左侧占位
        GridBagConstraints gbc_leftFiller = new GridBagConstraints();
        gbc_leftFiller.weightx = 1; // 使得这个组件吸收额外的水平空间
        gbc_leftFiller.gridx = 8; // 位置设置为第一个单元格
        gbc_leftFiller.gridy = 0; // 第一行
        gbc_leftFiller.fill = GridBagConstraints.HORIZONTAL; // 水平填充
        FilterPanel.add(Box.createHorizontalGlue(), gbc_leftFiller);

        // 设置按钮的 GridBagConstraints
        GridBagConstraints gbc_buttons = new GridBagConstraints();
        gbc_buttons.insets = new Insets(0, 5, 0, 5);
        gbc_buttons.gridx = 10; // 设置按钮的横坐标位置
        gbc_buttons.gridy = 0; // 设置按钮的纵坐标位置
        gbc_buttons.fill = GridBagConstraints.NONE; // 不填充

        // 在 FilterPanel 中添加 toggleButton
        FilterPanel.add(toggleButton, gbc_buttons);
        gbc_buttons.gridx = 11; // 将横坐标位置移动到下一个单元格
        FilterPanel.add(flashButton, gbc_buttons);
        gbc_buttons.gridx = 12; // 将横坐标位置移动到下一个单元格
        FilterPanel.add(flashText, gbc_buttons);

        // 添加填充以在右侧占位
        GridBagConstraints gbc_rightFiller = new GridBagConstraints();
        gbc_rightFiller.weightx = 1; // 使得这个组件吸收额外的水平空间
        gbc_rightFiller.gridx = 13; // 位置设置为最后一个单元格
        gbc_rightFiller.gridy = 0; // 第一行
        gbc_rightFiller.fill = GridBagConstraints.HORIZONTAL; // 水平填充
        FilterPanel.add(Box.createHorizontalGlue(), gbc_rightFiller);

        // 全部按钮
        choicesComboBox = new JComboBox<>(new String[]{"只看重点", "全部", "只看status为200", "只看敏感内容", "只看敏感路径"});
        GridBagConstraints gbc_btnall = new GridBagConstraints();
        gbc_btnall.insets = new Insets(0, 0, 0, 5);
        gbc_btnall.fill = 0;
        gbc_btnall.gridx = 15;  // 根据该值来确定是确定从左到右的顺序
        gbc_btnall.gridy = 0;
        FilterPanel.add(choicesComboBox, gbc_btnall);
        // 检索框
        searchField = new JTextField(15);
        GridBagConstraints gbc_btnSearchField = new GridBagConstraints();
        gbc_btnSearchField.insets = new Insets(0, 0, 0, 5);
        gbc_btnSearchField.fill = 0;
        gbc_btnSearchField.gridx = 16;  // 根据该值来确定是确定从左到右的顺序
        gbc_btnSearchField.gridy = 0;
        FilterPanel.add(searchField, gbc_btnSearchField);
        // 检索按钮
        JButton searchButton = new JButton();
        searchButton.setIcon(UiUtils.getImageIcon("/icon/searchButton.png"));
        searchButton.setToolTipText("搜索");
        GridBagConstraints gbc_btnSearch = new GridBagConstraints();
        gbc_btnSearch.insets = new Insets(0, 0, 0, 5);
        gbc_btnSearch.fill = 0;
        gbc_btnSearch.gridx = 17;  // 根据该值来确定是确定从左到右的顺序
        gbc_btnSearch.gridy = 0;
        FilterPanel.add(searchButton, gbc_btnSearch);

        // 功能按钮 弹出选项
        JPopupMenu moreMenu = new JPopupMenu("功能");
        JMenuItem resetItem = new JMenuItem("清除");
        resetItem.setIcon(UiUtils.getImageIcon("/icon/deleteButton.png"));
        moreMenu.add(resetItem);

//        JMenuItem exportItem = new JMenuItem("导出");
//        exportItem.setIcon(UiUtils.getImageIcon("/icon/exportItem.png", 17, 17));

        // 功能按钮
        JButton moreButton = new JButton();
        moreButton.setIcon(UiUtils.getImageIcon("/icon/moreButton.png", 17, 17));
        GridBagConstraints gbc_btnMore = new GridBagConstraints();
        gbc_btnMore.insets = new Insets(0, 0, 0, 5);
        gbc_btnMore.fill = 0;
        gbc_btnMore.gridx = 18;  // 根据该值来确定是确定从左到右的顺序
        gbc_btnMore.gridy = 0;
        FilterPanel.add(moreButton, gbc_btnMore);

        // 刷新按钮监听事件
        flashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 检查按钮的选中状态
                if (flashButton.isSelected()) {
                    // 如果按钮被选中，意味着刷新功能被激活，我们将文本设置为 "暂停刷新中"
                    flashText.setText("暂停每10秒刷新表格");
                } else {
                    // 如果按钮没有被选中，意味着刷新功能没有被激活，我们将文本设置为 "自动刷新"
                    flashText.setText("自动每10秒刷新表格中");
                }
            }
        });

        // 点击”功能“的监听事件
        moreButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                moreMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        // 为菜单项添加 Action Listener
        resetItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 清空表格模型中的所有行数据
                MainPanel.clearAllData();
                setFlashButtonTrue();
            }
        });

        // 检索按钮事件监听器
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                String selectedOption = (String)ConfigPanel.choicesComboBox.getSelectedItem();
                MainPanel.showFilter(selectedOption, searchText);
                setFlashButtonFalse();
                MainPanel.operationStartTime = LocalDateTime.now();
            }
        });


//        searchField.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String searchText = searchField.getText();
//                String selectedOption = (String)ConfigPanel.choicesComboBox.getSelectedItem();
//                MainPanel.showFilter(selectedOption, searchText);
//                setFlashButtonFalse();
//                MainPanel.operationStartTime = LocalDateTime.now();
//            }
//        });

//        // “全部” 按钮事件监听器
//        choicesComboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try{
//                    // 触发显示所有行事件
//                    String searchText = searchField.getText();
//                    if(searchText.isEmpty()){
//                        searchText = "";
//                    }
//                    String selectedOption = (String)choicesComboBox.getSelectedItem();
//                    if (selectedOption.equals("全部")){
//                        MainPanel.showFilter(selectedOption, searchText);
//                        setFlashButtonTrue();
//                    }else{
//                        MainPanel.showFilter(selectedOption, searchText);
//                        setFlashButtonFalse();
//                        MainPanel.operationStartTime = LocalDateTime.now();
//                    }
//                } catch (Exception ex) {
//                    BurpExtender.getStderr().println("[!] choicesComboBox:");
//                    ex.printStackTrace(BurpExtender.getStderr());
//                }
//            }
//        });
//
 }



    public static void setFlashButtonTrue(){
        flashButton.setSelected(false);
        flashText.setText("自动每10秒刷新表格中");
    }


    public static void setFlashButtonFalse(){
        flashButton.setSelected(true);
        flashText.setText("暂停定时刷新表格");
    }

    public static boolean getFlushButtonStatus(){
        // 检查按钮的选中状态
        if (flashButton.isSelected()) {
            // 如果按钮被选中，意味着刷新功能被激活，我们将文本设置为 "暂停刷新中"
            return true;
        } else {
            // 如果按钮没有被选中，意味着刷新功能没有被激活，我们将文本设置为 "自动刷新"
            return false;
        }
    }
}
