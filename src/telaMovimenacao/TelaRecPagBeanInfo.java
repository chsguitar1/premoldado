/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telaMovimenacao;

import java.beans.*;

/**
 *
 * @author cristiano
 */
public class TelaRecPagBeanInfo extends SimpleBeanInfo {

    // Bean descriptor//GEN-FIRST:BeanDescriptor
    /*lazy BeanDescriptor*/
    private static BeanDescriptor getBdescriptor(){
        BeanDescriptor beanDescriptor = new BeanDescriptor  ( telaMovimenacao.TelaRecPag.class , null ); // NOI18N//GEN-HEADEREND:BeanDescriptor
        beanDescriptor.setValue("containerDelegate", "getPainel");
        // Here you can add code for customizing the BeanDescriptor.

        return beanDescriptor;     }//GEN-LAST:BeanDescriptor
    // Property identifiers//GEN-FIRST:Properties
    private static final int PROPERTY_accessibleContext = 0;
    private static final int PROPERTY_actionMap = 1;
    private static final int PROPERTY_alignmentX = 2;
    private static final int PROPERTY_alignmentY = 3;
    private static final int PROPERTY_ancestorListeners = 4;
    private static final int PROPERTY_autoscrolls = 5;
    private static final int PROPERTY_background = 6;
    private static final int PROPERTY_backgroundSet = 7;
    private static final int PROPERTY_baselineResizeBehavior = 8;
    private static final int PROPERTY_border = 9;
    private static final int PROPERTY_bounds = 10;
    private static final int PROPERTY_closable = 11;
    private static final int PROPERTY_closed = 12;
    private static final int PROPERTY_colorModel = 13;
    private static final int PROPERTY_component = 14;
    private static final int PROPERTY_componentCount = 15;
    private static final int PROPERTY_componentListeners = 16;
    private static final int PROPERTY_componentOrientation = 17;
    private static final int PROPERTY_componentPopupMenu = 18;
    private static final int PROPERTY_components = 19;
    private static final int PROPERTY_containerListeners = 20;
    private static final int PROPERTY_contentPane = 21;
    private static final int PROPERTY_cursor = 22;
    private static final int PROPERTY_cursorSet = 23;
    private static final int PROPERTY_debugGraphicsOptions = 24;
    private static final int PROPERTY_defaultCloseOperation = 25;
    private static final int PROPERTY_desktopIcon = 26;
    private static final int PROPERTY_desktopPane = 27;
    private static final int PROPERTY_displayable = 28;
    private static final int PROPERTY_doubleBuffered = 29;
    private static final int PROPERTY_dropTarget = 30;
    private static final int PROPERTY_enabled = 31;
    private static final int PROPERTY_focusable = 32;
    private static final int PROPERTY_focusCycleRoot = 33;
    private static final int PROPERTY_focusCycleRootAncestor = 34;
    private static final int PROPERTY_focusListeners = 35;
    private static final int PROPERTY_focusOwner = 36;
    private static final int PROPERTY_focusTraversable = 37;
    private static final int PROPERTY_focusTraversalKeys = 38;
    private static final int PROPERTY_focusTraversalKeysEnabled = 39;
    private static final int PROPERTY_focusTraversalPolicy = 40;
    private static final int PROPERTY_focusTraversalPolicyProvider = 41;
    private static final int PROPERTY_focusTraversalPolicySet = 42;
    private static final int PROPERTY_font = 43;
    private static final int PROPERTY_fontSet = 44;
    private static final int PROPERTY_foreground = 45;
    private static final int PROPERTY_foregroundSet = 46;
    private static final int PROPERTY_frameIcon = 47;
    private static final int PROPERTY_glassPane = 48;
    private static final int PROPERTY_graphics = 49;
    private static final int PROPERTY_graphicsConfiguration = 50;
    private static final int PROPERTY_height = 51;
    private static final int PROPERTY_hierarchyBoundsListeners = 52;
    private static final int PROPERTY_hierarchyListeners = 53;
    private static final int PROPERTY_icon = 54;
    private static final int PROPERTY_iconifiable = 55;
    private static final int PROPERTY_ignoreRepaint = 56;
    private static final int PROPERTY_inheritsPopupMenu = 57;
    private static final int PROPERTY_inputContext = 58;
    private static final int PROPERTY_inputMap = 59;
    private static final int PROPERTY_inputMethodListeners = 60;
    private static final int PROPERTY_inputMethodRequests = 61;
    private static final int PROPERTY_inputVerifier = 62;
    private static final int PROPERTY_insets = 63;
    private static final int PROPERTY_internalFrameListeners = 64;
    private static final int PROPERTY_JMenuBar = 65;
    private static final int PROPERTY_keyListeners = 66;
    private static final int PROPERTY_lastCursor = 67;
    private static final int PROPERTY_layer = 68;
    private static final int PROPERTY_layeredPane = 69;
    private static final int PROPERTY_layout = 70;
    private static final int PROPERTY_lightweight = 71;
    private static final int PROPERTY_locale = 72;
    private static final int PROPERTY_location = 73;
    private static final int PROPERTY_locationOnScreen = 74;
    private static final int PROPERTY_managingFocus = 75;
    private static final int PROPERTY_maximizable = 76;
    private static final int PROPERTY_maximum = 77;
    private static final int PROPERTY_maximumSize = 78;
    private static final int PROPERTY_maximumSizeSet = 79;
    private static final int PROPERTY_menuBar = 80;
    private static final int PROPERTY_minimumSize = 81;
    private static final int PROPERTY_minimumSizeSet = 82;
    private static final int PROPERTY_mostRecentFocusOwner = 83;
    private static final int PROPERTY_mouseListeners = 84;
    private static final int PROPERTY_mouseMotionListeners = 85;
    private static final int PROPERTY_mousePosition = 86;
    private static final int PROPERTY_mouseWheelListeners = 87;
    private static final int PROPERTY_name = 88;
    private static final int PROPERTY_nextFocusableComponent = 89;
    private static final int PROPERTY_normalBounds = 90;
    private static final int PROPERTY_opaque = 91;
    private static final int PROPERTY_optimizedDrawingEnabled = 92;
    private static final int PROPERTY_paintingForPrint = 93;
    private static final int PROPERTY_paintingTile = 94;
    private static final int PROPERTY_parent = 95;
    private static final int PROPERTY_peer = 96;
    private static final int PROPERTY_preferredSize = 97;
    private static final int PROPERTY_preferredSizeSet = 98;
    private static final int PROPERTY_propertyChangeListeners = 99;
    private static final int PROPERTY_registeredKeyStrokes = 100;
    private static final int PROPERTY_requestFocusEnabled = 101;
    private static final int PROPERTY_resizable = 102;
    private static final int PROPERTY_rootPane = 103;
    private static final int PROPERTY_selected = 104;
    private static final int PROPERTY_showing = 105;
    private static final int PROPERTY_size = 106;
    private static final int PROPERTY_title = 107;
    private static final int PROPERTY_toolkit = 108;
    private static final int PROPERTY_toolTipText = 109;
    private static final int PROPERTY_topLevelAncestor = 110;
    private static final int PROPERTY_transferHandler = 111;
    private static final int PROPERTY_treeLock = 112;
    private static final int PROPERTY_UI = 113;
    private static final int PROPERTY_UIClassID = 114;
    private static final int PROPERTY_valid = 115;
    private static final int PROPERTY_validateRoot = 116;
    private static final int PROPERTY_verifyInputWhenFocusTarget = 117;
    private static final int PROPERTY_vetoableChangeListeners = 118;
    private static final int PROPERTY_visible = 119;
    private static final int PROPERTY_visibleRect = 120;
    private static final int PROPERTY_warningString = 121;
    private static final int PROPERTY_width = 122;
    private static final int PROPERTY_x = 123;
    private static final int PROPERTY_y = 124;

    // Property array 
    /*lazy PropertyDescriptor*/
    private static PropertyDescriptor[] getPdescriptor(){
        PropertyDescriptor[] properties = new PropertyDescriptor[125];
    
        try {
            properties[PROPERTY_accessibleContext] = new PropertyDescriptor ( "accessibleContext", telaMovimenacao.TelaRecPag.class, "getAccessibleContext", null ); // NOI18N
            properties[PROPERTY_actionMap] = new PropertyDescriptor ( "actionMap", telaMovimenacao.TelaRecPag.class, "getActionMap", "setActionMap" ); // NOI18N
            properties[PROPERTY_alignmentX] = new PropertyDescriptor ( "alignmentX", telaMovimenacao.TelaRecPag.class, "getAlignmentX", "setAlignmentX" ); // NOI18N
            properties[PROPERTY_alignmentY] = new PropertyDescriptor ( "alignmentY", telaMovimenacao.TelaRecPag.class, "getAlignmentY", "setAlignmentY" ); // NOI18N
            properties[PROPERTY_ancestorListeners] = new PropertyDescriptor ( "ancestorListeners", telaMovimenacao.TelaRecPag.class, "getAncestorListeners", null ); // NOI18N
            properties[PROPERTY_autoscrolls] = new PropertyDescriptor ( "autoscrolls", telaMovimenacao.TelaRecPag.class, "getAutoscrolls", "setAutoscrolls" ); // NOI18N
            properties[PROPERTY_background] = new PropertyDescriptor ( "background", telaMovimenacao.TelaRecPag.class, "getBackground", "setBackground" ); // NOI18N
            properties[PROPERTY_backgroundSet] = new PropertyDescriptor ( "backgroundSet", telaMovimenacao.TelaRecPag.class, "isBackgroundSet", null ); // NOI18N
            properties[PROPERTY_baselineResizeBehavior] = new PropertyDescriptor ( "baselineResizeBehavior", telaMovimenacao.TelaRecPag.class, "getBaselineResizeBehavior", null ); // NOI18N
            properties[PROPERTY_border] = new PropertyDescriptor ( "border", telaMovimenacao.TelaRecPag.class, "getBorder", "setBorder" ); // NOI18N
            properties[PROPERTY_bounds] = new PropertyDescriptor ( "bounds", telaMovimenacao.TelaRecPag.class, "getBounds", "setBounds" ); // NOI18N
            properties[PROPERTY_closable] = new PropertyDescriptor ( "closable", telaMovimenacao.TelaRecPag.class, "isClosable", "setClosable" ); // NOI18N
            properties[PROPERTY_closed] = new PropertyDescriptor ( "closed", telaMovimenacao.TelaRecPag.class, "isClosed", "setClosed" ); // NOI18N
            properties[PROPERTY_colorModel] = new PropertyDescriptor ( "colorModel", telaMovimenacao.TelaRecPag.class, "getColorModel", null ); // NOI18N
            properties[PROPERTY_component] = new IndexedPropertyDescriptor ( "component", telaMovimenacao.TelaRecPag.class, null, null, "getComponent", null ); // NOI18N
            properties[PROPERTY_componentCount] = new PropertyDescriptor ( "componentCount", telaMovimenacao.TelaRecPag.class, "getComponentCount", null ); // NOI18N
            properties[PROPERTY_componentListeners] = new PropertyDescriptor ( "componentListeners", telaMovimenacao.TelaRecPag.class, "getComponentListeners", null ); // NOI18N
            properties[PROPERTY_componentOrientation] = new PropertyDescriptor ( "componentOrientation", telaMovimenacao.TelaRecPag.class, "getComponentOrientation", "setComponentOrientation" ); // NOI18N
            properties[PROPERTY_componentPopupMenu] = new PropertyDescriptor ( "componentPopupMenu", telaMovimenacao.TelaRecPag.class, "getComponentPopupMenu", "setComponentPopupMenu" ); // NOI18N
            properties[PROPERTY_components] = new PropertyDescriptor ( "components", telaMovimenacao.TelaRecPag.class, "getComponents", null ); // NOI18N
            properties[PROPERTY_containerListeners] = new PropertyDescriptor ( "containerListeners", telaMovimenacao.TelaRecPag.class, "getContainerListeners", null ); // NOI18N
            properties[PROPERTY_contentPane] = new PropertyDescriptor ( "contentPane", telaMovimenacao.TelaRecPag.class, "getContentPane", "setContentPane" ); // NOI18N
            properties[PROPERTY_cursor] = new PropertyDescriptor ( "cursor", telaMovimenacao.TelaRecPag.class, "getCursor", "setCursor" ); // NOI18N
            properties[PROPERTY_cursorSet] = new PropertyDescriptor ( "cursorSet", telaMovimenacao.TelaRecPag.class, "isCursorSet", null ); // NOI18N
            properties[PROPERTY_debugGraphicsOptions] = new PropertyDescriptor ( "debugGraphicsOptions", telaMovimenacao.TelaRecPag.class, "getDebugGraphicsOptions", "setDebugGraphicsOptions" ); // NOI18N
            properties[PROPERTY_defaultCloseOperation] = new PropertyDescriptor ( "defaultCloseOperation", telaMovimenacao.TelaRecPag.class, "getDefaultCloseOperation", "setDefaultCloseOperation" ); // NOI18N
            properties[PROPERTY_desktopIcon] = new PropertyDescriptor ( "desktopIcon", telaMovimenacao.TelaRecPag.class, "getDesktopIcon", "setDesktopIcon" ); // NOI18N
            properties[PROPERTY_desktopPane] = new PropertyDescriptor ( "desktopPane", telaMovimenacao.TelaRecPag.class, "getDesktopPane", null ); // NOI18N
            properties[PROPERTY_displayable] = new PropertyDescriptor ( "displayable", telaMovimenacao.TelaRecPag.class, "isDisplayable", null ); // NOI18N
            properties[PROPERTY_doubleBuffered] = new PropertyDescriptor ( "doubleBuffered", telaMovimenacao.TelaRecPag.class, "isDoubleBuffered", "setDoubleBuffered" ); // NOI18N
            properties[PROPERTY_dropTarget] = new PropertyDescriptor ( "dropTarget", telaMovimenacao.TelaRecPag.class, "getDropTarget", "setDropTarget" ); // NOI18N
            properties[PROPERTY_enabled] = new PropertyDescriptor ( "enabled", telaMovimenacao.TelaRecPag.class, "isEnabled", "setEnabled" ); // NOI18N
            properties[PROPERTY_focusable] = new PropertyDescriptor ( "focusable", telaMovimenacao.TelaRecPag.class, "isFocusable", "setFocusable" ); // NOI18N
            properties[PROPERTY_focusCycleRoot] = new PropertyDescriptor ( "focusCycleRoot", telaMovimenacao.TelaRecPag.class, "isFocusCycleRoot", "setFocusCycleRoot" ); // NOI18N
            properties[PROPERTY_focusCycleRootAncestor] = new PropertyDescriptor ( "focusCycleRootAncestor", telaMovimenacao.TelaRecPag.class, "getFocusCycleRootAncestor", null ); // NOI18N
            properties[PROPERTY_focusListeners] = new PropertyDescriptor ( "focusListeners", telaMovimenacao.TelaRecPag.class, "getFocusListeners", null ); // NOI18N
            properties[PROPERTY_focusOwner] = new PropertyDescriptor ( "focusOwner", telaMovimenacao.TelaRecPag.class, "getFocusOwner", null ); // NOI18N
            properties[PROPERTY_focusTraversable] = new PropertyDescriptor ( "focusTraversable", telaMovimenacao.TelaRecPag.class, "isFocusTraversable", null ); // NOI18N
            properties[PROPERTY_focusTraversalKeys] = new IndexedPropertyDescriptor ( "focusTraversalKeys", telaMovimenacao.TelaRecPag.class, null, null, null, "setFocusTraversalKeys" ); // NOI18N
            properties[PROPERTY_focusTraversalKeysEnabled] = new PropertyDescriptor ( "focusTraversalKeysEnabled", telaMovimenacao.TelaRecPag.class, "getFocusTraversalKeysEnabled", "setFocusTraversalKeysEnabled" ); // NOI18N
            properties[PROPERTY_focusTraversalPolicy] = new PropertyDescriptor ( "focusTraversalPolicy", telaMovimenacao.TelaRecPag.class, "getFocusTraversalPolicy", "setFocusTraversalPolicy" ); // NOI18N
            properties[PROPERTY_focusTraversalPolicyProvider] = new PropertyDescriptor ( "focusTraversalPolicyProvider", telaMovimenacao.TelaRecPag.class, "isFocusTraversalPolicyProvider", "setFocusTraversalPolicyProvider" ); // NOI18N
            properties[PROPERTY_focusTraversalPolicySet] = new PropertyDescriptor ( "focusTraversalPolicySet", telaMovimenacao.TelaRecPag.class, "isFocusTraversalPolicySet", null ); // NOI18N
            properties[PROPERTY_font] = new PropertyDescriptor ( "font", telaMovimenacao.TelaRecPag.class, "getFont", "setFont" ); // NOI18N
            properties[PROPERTY_fontSet] = new PropertyDescriptor ( "fontSet", telaMovimenacao.TelaRecPag.class, "isFontSet", null ); // NOI18N
            properties[PROPERTY_foreground] = new PropertyDescriptor ( "foreground", telaMovimenacao.TelaRecPag.class, "getForeground", "setForeground" ); // NOI18N
            properties[PROPERTY_foregroundSet] = new PropertyDescriptor ( "foregroundSet", telaMovimenacao.TelaRecPag.class, "isForegroundSet", null ); // NOI18N
            properties[PROPERTY_frameIcon] = new PropertyDescriptor ( "frameIcon", telaMovimenacao.TelaRecPag.class, "getFrameIcon", "setFrameIcon" ); // NOI18N
            properties[PROPERTY_glassPane] = new PropertyDescriptor ( "glassPane", telaMovimenacao.TelaRecPag.class, "getGlassPane", "setGlassPane" ); // NOI18N
            properties[PROPERTY_graphics] = new PropertyDescriptor ( "graphics", telaMovimenacao.TelaRecPag.class, "getGraphics", null ); // NOI18N
            properties[PROPERTY_graphicsConfiguration] = new PropertyDescriptor ( "graphicsConfiguration", telaMovimenacao.TelaRecPag.class, "getGraphicsConfiguration", null ); // NOI18N
            properties[PROPERTY_height] = new PropertyDescriptor ( "height", telaMovimenacao.TelaRecPag.class, "getHeight", null ); // NOI18N
            properties[PROPERTY_hierarchyBoundsListeners] = new PropertyDescriptor ( "hierarchyBoundsListeners", telaMovimenacao.TelaRecPag.class, "getHierarchyBoundsListeners", null ); // NOI18N
            properties[PROPERTY_hierarchyListeners] = new PropertyDescriptor ( "hierarchyListeners", telaMovimenacao.TelaRecPag.class, "getHierarchyListeners", null ); // NOI18N
            properties[PROPERTY_icon] = new PropertyDescriptor ( "icon", telaMovimenacao.TelaRecPag.class, "isIcon", "setIcon" ); // NOI18N
            properties[PROPERTY_iconifiable] = new PropertyDescriptor ( "iconifiable", telaMovimenacao.TelaRecPag.class, "isIconifiable", "setIconifiable" ); // NOI18N
            properties[PROPERTY_ignoreRepaint] = new PropertyDescriptor ( "ignoreRepaint", telaMovimenacao.TelaRecPag.class, "getIgnoreRepaint", "setIgnoreRepaint" ); // NOI18N
            properties[PROPERTY_inheritsPopupMenu] = new PropertyDescriptor ( "inheritsPopupMenu", telaMovimenacao.TelaRecPag.class, "getInheritsPopupMenu", "setInheritsPopupMenu" ); // NOI18N
            properties[PROPERTY_inputContext] = new PropertyDescriptor ( "inputContext", telaMovimenacao.TelaRecPag.class, "getInputContext", null ); // NOI18N
            properties[PROPERTY_inputMap] = new PropertyDescriptor ( "inputMap", telaMovimenacao.TelaRecPag.class, "getInputMap", null ); // NOI18N
            properties[PROPERTY_inputMethodListeners] = new PropertyDescriptor ( "inputMethodListeners", telaMovimenacao.TelaRecPag.class, "getInputMethodListeners", null ); // NOI18N
            properties[PROPERTY_inputMethodRequests] = new PropertyDescriptor ( "inputMethodRequests", telaMovimenacao.TelaRecPag.class, "getInputMethodRequests", null ); // NOI18N
            properties[PROPERTY_inputVerifier] = new PropertyDescriptor ( "inputVerifier", telaMovimenacao.TelaRecPag.class, "getInputVerifier", "setInputVerifier" ); // NOI18N
            properties[PROPERTY_insets] = new PropertyDescriptor ( "insets", telaMovimenacao.TelaRecPag.class, "getInsets", null ); // NOI18N
            properties[PROPERTY_internalFrameListeners] = new PropertyDescriptor ( "internalFrameListeners", telaMovimenacao.TelaRecPag.class, "getInternalFrameListeners", null ); // NOI18N
            properties[PROPERTY_JMenuBar] = new PropertyDescriptor ( "JMenuBar", telaMovimenacao.TelaRecPag.class, "getJMenuBar", "setJMenuBar" ); // NOI18N
            properties[PROPERTY_keyListeners] = new PropertyDescriptor ( "keyListeners", telaMovimenacao.TelaRecPag.class, "getKeyListeners", null ); // NOI18N
            properties[PROPERTY_lastCursor] = new PropertyDescriptor ( "lastCursor", telaMovimenacao.TelaRecPag.class, "getLastCursor", null ); // NOI18N
            properties[PROPERTY_layer] = new PropertyDescriptor ( "layer", telaMovimenacao.TelaRecPag.class, "getLayer", "setLayer" ); // NOI18N
            properties[PROPERTY_layeredPane] = new PropertyDescriptor ( "layeredPane", telaMovimenacao.TelaRecPag.class, "getLayeredPane", "setLayeredPane" ); // NOI18N
            properties[PROPERTY_layout] = new PropertyDescriptor ( "layout", telaMovimenacao.TelaRecPag.class, "getLayout", "setLayout" ); // NOI18N
            properties[PROPERTY_lightweight] = new PropertyDescriptor ( "lightweight", telaMovimenacao.TelaRecPag.class, "isLightweight", null ); // NOI18N
            properties[PROPERTY_locale] = new PropertyDescriptor ( "locale", telaMovimenacao.TelaRecPag.class, "getLocale", "setLocale" ); // NOI18N
            properties[PROPERTY_location] = new PropertyDescriptor ( "location", telaMovimenacao.TelaRecPag.class, "getLocation", "setLocation" ); // NOI18N
            properties[PROPERTY_locationOnScreen] = new PropertyDescriptor ( "locationOnScreen", telaMovimenacao.TelaRecPag.class, "getLocationOnScreen", null ); // NOI18N
            properties[PROPERTY_managingFocus] = new PropertyDescriptor ( "managingFocus", telaMovimenacao.TelaRecPag.class, "isManagingFocus", null ); // NOI18N
            properties[PROPERTY_maximizable] = new PropertyDescriptor ( "maximizable", telaMovimenacao.TelaRecPag.class, "isMaximizable", "setMaximizable" ); // NOI18N
            properties[PROPERTY_maximum] = new PropertyDescriptor ( "maximum", telaMovimenacao.TelaRecPag.class, "isMaximum", "setMaximum" ); // NOI18N
            properties[PROPERTY_maximumSize] = new PropertyDescriptor ( "maximumSize", telaMovimenacao.TelaRecPag.class, "getMaximumSize", "setMaximumSize" ); // NOI18N
            properties[PROPERTY_maximumSizeSet] = new PropertyDescriptor ( "maximumSizeSet", telaMovimenacao.TelaRecPag.class, "isMaximumSizeSet", null ); // NOI18N
            properties[PROPERTY_menuBar] = new PropertyDescriptor ( "menuBar", telaMovimenacao.TelaRecPag.class, "getMenuBar", "setMenuBar" ); // NOI18N
            properties[PROPERTY_minimumSize] = new PropertyDescriptor ( "minimumSize", telaMovimenacao.TelaRecPag.class, "getMinimumSize", "setMinimumSize" ); // NOI18N
            properties[PROPERTY_minimumSizeSet] = new PropertyDescriptor ( "minimumSizeSet", telaMovimenacao.TelaRecPag.class, "isMinimumSizeSet", null ); // NOI18N
            properties[PROPERTY_mostRecentFocusOwner] = new PropertyDescriptor ( "mostRecentFocusOwner", telaMovimenacao.TelaRecPag.class, "getMostRecentFocusOwner", null ); // NOI18N
            properties[PROPERTY_mouseListeners] = new PropertyDescriptor ( "mouseListeners", telaMovimenacao.TelaRecPag.class, "getMouseListeners", null ); // NOI18N
            properties[PROPERTY_mouseMotionListeners] = new PropertyDescriptor ( "mouseMotionListeners", telaMovimenacao.TelaRecPag.class, "getMouseMotionListeners", null ); // NOI18N
            properties[PROPERTY_mousePosition] = new PropertyDescriptor ( "mousePosition", telaMovimenacao.TelaRecPag.class, "getMousePosition", null ); // NOI18N
            properties[PROPERTY_mouseWheelListeners] = new PropertyDescriptor ( "mouseWheelListeners", telaMovimenacao.TelaRecPag.class, "getMouseWheelListeners", null ); // NOI18N
            properties[PROPERTY_name] = new PropertyDescriptor ( "name", telaMovimenacao.TelaRecPag.class, "getName", "setName" ); // NOI18N
            properties[PROPERTY_nextFocusableComponent] = new PropertyDescriptor ( "nextFocusableComponent", telaMovimenacao.TelaRecPag.class, "getNextFocusableComponent", "setNextFocusableComponent" ); // NOI18N
            properties[PROPERTY_normalBounds] = new PropertyDescriptor ( "normalBounds", telaMovimenacao.TelaRecPag.class, "getNormalBounds", "setNormalBounds" ); // NOI18N
            properties[PROPERTY_opaque] = new PropertyDescriptor ( "opaque", telaMovimenacao.TelaRecPag.class, "isOpaque", "setOpaque" ); // NOI18N
            properties[PROPERTY_optimizedDrawingEnabled] = new PropertyDescriptor ( "optimizedDrawingEnabled", telaMovimenacao.TelaRecPag.class, "isOptimizedDrawingEnabled", null ); // NOI18N
            properties[PROPERTY_paintingForPrint] = new PropertyDescriptor ( "paintingForPrint", telaMovimenacao.TelaRecPag.class, "isPaintingForPrint", null ); // NOI18N
            properties[PROPERTY_paintingTile] = new PropertyDescriptor ( "paintingTile", telaMovimenacao.TelaRecPag.class, "isPaintingTile", null ); // NOI18N
            properties[PROPERTY_parent] = new PropertyDescriptor ( "parent", telaMovimenacao.TelaRecPag.class, "getParent", null ); // NOI18N
            properties[PROPERTY_peer] = new PropertyDescriptor ( "peer", telaMovimenacao.TelaRecPag.class, "getPeer", null ); // NOI18N
            properties[PROPERTY_preferredSize] = new PropertyDescriptor ( "preferredSize", telaMovimenacao.TelaRecPag.class, "getPreferredSize", "setPreferredSize" ); // NOI18N
            properties[PROPERTY_preferredSizeSet] = new PropertyDescriptor ( "preferredSizeSet", telaMovimenacao.TelaRecPag.class, "isPreferredSizeSet", null ); // NOI18N
            properties[PROPERTY_propertyChangeListeners] = new PropertyDescriptor ( "propertyChangeListeners", telaMovimenacao.TelaRecPag.class, "getPropertyChangeListeners", null ); // NOI18N
            properties[PROPERTY_registeredKeyStrokes] = new PropertyDescriptor ( "registeredKeyStrokes", telaMovimenacao.TelaRecPag.class, "getRegisteredKeyStrokes", null ); // NOI18N
            properties[PROPERTY_requestFocusEnabled] = new PropertyDescriptor ( "requestFocusEnabled", telaMovimenacao.TelaRecPag.class, "isRequestFocusEnabled", "setRequestFocusEnabled" ); // NOI18N
            properties[PROPERTY_resizable] = new PropertyDescriptor ( "resizable", telaMovimenacao.TelaRecPag.class, "isResizable", "setResizable" ); // NOI18N
            properties[PROPERTY_rootPane] = new PropertyDescriptor ( "rootPane", telaMovimenacao.TelaRecPag.class, "getRootPane", null ); // NOI18N
            properties[PROPERTY_selected] = new PropertyDescriptor ( "selected", telaMovimenacao.TelaRecPag.class, "isSelected", "setSelected" ); // NOI18N
            properties[PROPERTY_showing] = new PropertyDescriptor ( "showing", telaMovimenacao.TelaRecPag.class, "isShowing", null ); // NOI18N
            properties[PROPERTY_size] = new PropertyDescriptor ( "size", telaMovimenacao.TelaRecPag.class, "getSize", "setSize" ); // NOI18N
            properties[PROPERTY_title] = new PropertyDescriptor ( "title", telaMovimenacao.TelaRecPag.class, "getTitle", "setTitle" ); // NOI18N
            properties[PROPERTY_toolkit] = new PropertyDescriptor ( "toolkit", telaMovimenacao.TelaRecPag.class, "getToolkit", null ); // NOI18N
            properties[PROPERTY_toolTipText] = new PropertyDescriptor ( "toolTipText", telaMovimenacao.TelaRecPag.class, "getToolTipText", "setToolTipText" ); // NOI18N
            properties[PROPERTY_topLevelAncestor] = new PropertyDescriptor ( "topLevelAncestor", telaMovimenacao.TelaRecPag.class, "getTopLevelAncestor", null ); // NOI18N
            properties[PROPERTY_transferHandler] = new PropertyDescriptor ( "transferHandler", telaMovimenacao.TelaRecPag.class, "getTransferHandler", "setTransferHandler" ); // NOI18N
            properties[PROPERTY_treeLock] = new PropertyDescriptor ( "treeLock", telaMovimenacao.TelaRecPag.class, "getTreeLock", null ); // NOI18N
            properties[PROPERTY_UI] = new PropertyDescriptor ( "UI", telaMovimenacao.TelaRecPag.class, "getUI", "setUI" ); // NOI18N
            properties[PROPERTY_UIClassID] = new PropertyDescriptor ( "UIClassID", telaMovimenacao.TelaRecPag.class, "getUIClassID", null ); // NOI18N
            properties[PROPERTY_valid] = new PropertyDescriptor ( "valid", telaMovimenacao.TelaRecPag.class, "isValid", null ); // NOI18N
            properties[PROPERTY_validateRoot] = new PropertyDescriptor ( "validateRoot", telaMovimenacao.TelaRecPag.class, "isValidateRoot", null ); // NOI18N
            properties[PROPERTY_verifyInputWhenFocusTarget] = new PropertyDescriptor ( "verifyInputWhenFocusTarget", telaMovimenacao.TelaRecPag.class, "getVerifyInputWhenFocusTarget", "setVerifyInputWhenFocusTarget" ); // NOI18N
            properties[PROPERTY_vetoableChangeListeners] = new PropertyDescriptor ( "vetoableChangeListeners", telaMovimenacao.TelaRecPag.class, "getVetoableChangeListeners", null ); // NOI18N
            properties[PROPERTY_visible] = new PropertyDescriptor ( "visible", telaMovimenacao.TelaRecPag.class, "isVisible", "setVisible" ); // NOI18N
            properties[PROPERTY_visibleRect] = new PropertyDescriptor ( "visibleRect", telaMovimenacao.TelaRecPag.class, "getVisibleRect", null ); // NOI18N
            properties[PROPERTY_warningString] = new PropertyDescriptor ( "warningString", telaMovimenacao.TelaRecPag.class, "getWarningString", null ); // NOI18N
            properties[PROPERTY_width] = new PropertyDescriptor ( "width", telaMovimenacao.TelaRecPag.class, "getWidth", null ); // NOI18N
            properties[PROPERTY_x] = new PropertyDescriptor ( "x", telaMovimenacao.TelaRecPag.class, "getX", null ); // NOI18N
            properties[PROPERTY_y] = new PropertyDescriptor ( "y", telaMovimenacao.TelaRecPag.class, "getY", null ); // NOI18N
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Properties

        // Here you can add code for customizing the properties array.

        return properties;     }//GEN-LAST:Properties
    // EventSet identifiers//GEN-FIRST:Events
    private static final int EVENT_ancestorListener = 0;
    private static final int EVENT_componentListener = 1;
    private static final int EVENT_containerListener = 2;
    private static final int EVENT_focusListener = 3;
    private static final int EVENT_hierarchyBoundsListener = 4;
    private static final int EVENT_hierarchyListener = 5;
    private static final int EVENT_inputMethodListener = 6;
    private static final int EVENT_internalFrameListener = 7;
    private static final int EVENT_keyListener = 8;
    private static final int EVENT_mouseListener = 9;
    private static final int EVENT_mouseMotionListener = 10;
    private static final int EVENT_mouseWheelListener = 11;
    private static final int EVENT_propertyChangeListener = 12;
    private static final int EVENT_vetoableChangeListener = 13;

    // EventSet array
    /*lazy EventSetDescriptor*/
    private static EventSetDescriptor[] getEdescriptor(){
        EventSetDescriptor[] eventSets = new EventSetDescriptor[14];
    
        try {
            eventSets[EVENT_ancestorListener] = new EventSetDescriptor ( telaMovimenacao.TelaRecPag.class, "ancestorListener", javax.swing.event.AncestorListener.class, new String[] {"ancestorAdded", "ancestorRemoved", "ancestorMoved"}, "addAncestorListener", "removeAncestorListener" ); // NOI18N
            eventSets[EVENT_componentListener] = new EventSetDescriptor ( telaMovimenacao.TelaRecPag.class, "componentListener", java.awt.event.ComponentListener.class, new String[] {"componentResized", "componentMoved", "componentShown", "componentHidden"}, "addComponentListener", "removeComponentListener" ); // NOI18N
            eventSets[EVENT_containerListener] = new EventSetDescriptor ( telaMovimenacao.TelaRecPag.class, "containerListener", java.awt.event.ContainerListener.class, new String[] {"componentAdded", "componentRemoved"}, "addContainerListener", "removeContainerListener" ); // NOI18N
            eventSets[EVENT_focusListener] = new EventSetDescriptor ( telaMovimenacao.TelaRecPag.class, "focusListener", java.awt.event.FocusListener.class, new String[] {"focusGained", "focusLost"}, "addFocusListener", "removeFocusListener" ); // NOI18N
            eventSets[EVENT_hierarchyBoundsListener] = new EventSetDescriptor ( telaMovimenacao.TelaRecPag.class, "hierarchyBoundsListener", java.awt.event.HierarchyBoundsListener.class, new String[] {"ancestorMoved", "ancestorResized"}, "addHierarchyBoundsListener", "removeHierarchyBoundsListener" ); // NOI18N
            eventSets[EVENT_hierarchyListener] = new EventSetDescriptor ( telaMovimenacao.TelaRecPag.class, "hierarchyListener", java.awt.event.HierarchyListener.class, new String[] {"hierarchyChanged"}, "addHierarchyListener", "removeHierarchyListener" ); // NOI18N
            eventSets[EVENT_inputMethodListener] = new EventSetDescriptor ( telaMovimenacao.TelaRecPag.class, "inputMethodListener", java.awt.event.InputMethodListener.class, new String[] {"inputMethodTextChanged", "caretPositionChanged"}, "addInputMethodListener", "removeInputMethodListener" ); // NOI18N
            eventSets[EVENT_internalFrameListener] = new EventSetDescriptor ( telaMovimenacao.TelaRecPag.class, "internalFrameListener", javax.swing.event.InternalFrameListener.class, new String[] {"internalFrameOpened", "internalFrameClosing", "internalFrameClosed", "internalFrameIconified", "internalFrameDeiconified", "internalFrameActivated", "internalFrameDeactivated"}, "addInternalFrameListener", "removeInternalFrameListener" ); // NOI18N
            eventSets[EVENT_keyListener] = new EventSetDescriptor ( telaMovimenacao.TelaRecPag.class, "keyListener", java.awt.event.KeyListener.class, new String[] {"keyTyped", "keyPressed", "keyReleased"}, "addKeyListener", "removeKeyListener" ); // NOI18N
            eventSets[EVENT_mouseListener] = new EventSetDescriptor ( telaMovimenacao.TelaRecPag.class, "mouseListener", java.awt.event.MouseListener.class, new String[] {"mouseClicked", "mousePressed", "mouseReleased", "mouseEntered", "mouseExited"}, "addMouseListener", "removeMouseListener" ); // NOI18N
            eventSets[EVENT_mouseMotionListener] = new EventSetDescriptor ( telaMovimenacao.TelaRecPag.class, "mouseMotionListener", java.awt.event.MouseMotionListener.class, new String[] {"mouseDragged", "mouseMoved"}, "addMouseMotionListener", "removeMouseMotionListener" ); // NOI18N
            eventSets[EVENT_mouseWheelListener] = new EventSetDescriptor ( telaMovimenacao.TelaRecPag.class, "mouseWheelListener", java.awt.event.MouseWheelListener.class, new String[] {"mouseWheelMoved"}, "addMouseWheelListener", "removeMouseWheelListener" ); // NOI18N
            eventSets[EVENT_propertyChangeListener] = new EventSetDescriptor ( telaMovimenacao.TelaRecPag.class, "propertyChangeListener", java.beans.PropertyChangeListener.class, new String[] {"propertyChange"}, "addPropertyChangeListener", "removePropertyChangeListener" ); // NOI18N
            eventSets[EVENT_vetoableChangeListener] = new EventSetDescriptor ( telaMovimenacao.TelaRecPag.class, "vetoableChangeListener", java.beans.VetoableChangeListener.class, new String[] {"vetoableChange"}, "addVetoableChangeListener", "removeVetoableChangeListener" ); // NOI18N
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Events

        // Here you can add code for customizing the event sets array.

        return eventSets;     }//GEN-LAST:Events
    // Method identifiers//GEN-FIRST:Methods
    private static final int METHOD_action0 = 0;
    private static final int METHOD_add1 = 1;
    private static final int METHOD_add2 = 2;
    private static final int METHOD_add3 = 3;
    private static final int METHOD_add4 = 4;
    private static final int METHOD_add5 = 5;
    private static final int METHOD_add6 = 6;
    private static final int METHOD_addNotify7 = 7;
    private static final int METHOD_addPropertyChangeListener8 = 8;
    private static final int METHOD_applyComponentOrientation9 = 9;
    private static final int METHOD_areFocusTraversalKeysSet10 = 10;
    private static final int METHOD_bounds11 = 11;
    private static final int METHOD_checkImage12 = 12;
    private static final int METHOD_checkImage13 = 13;
    private static final int METHOD_computeVisibleRect14 = 14;
    private static final int METHOD_consultarDados15 = 15;
    private static final int METHOD_contains16 = 16;
    private static final int METHOD_contains17 = 17;
    private static final int METHOD_countComponents18 = 18;
    private static final int METHOD_createImage19 = 19;
    private static final int METHOD_createImage20 = 20;
    private static final int METHOD_createToolTip21 = 21;
    private static final int METHOD_createVolatileImage22 = 22;
    private static final int METHOD_createVolatileImage23 = 23;
    private static final int METHOD_deliverEvent24 = 24;
    private static final int METHOD_disable25 = 25;
    private static final int METHOD_dispatchEvent26 = 26;
    private static final int METHOD_dispose27 = 27;
    private static final int METHOD_doDefaultCloseAction28 = 28;
    private static final int METHOD_doLayout29 = 29;
    private static final int METHOD_enable30 = 30;
    private static final int METHOD_enable31 = 31;
    private static final int METHOD_enableInputMethods32 = 32;
    private static final int METHOD_findComponentAt33 = 33;
    private static final int METHOD_findComponentAt34 = 34;
    private static final int METHOD_firePropertyChange35 = 35;
    private static final int METHOD_firePropertyChange36 = 36;
    private static final int METHOD_firePropertyChange37 = 37;
    private static final int METHOD_firePropertyChange38 = 38;
    private static final int METHOD_firePropertyChange39 = 39;
    private static final int METHOD_firePropertyChange40 = 40;
    private static final int METHOD_firePropertyChange41 = 41;
    private static final int METHOD_firePropertyChange42 = 42;
    private static final int METHOD_getActionForKeyStroke43 = 43;
    private static final int METHOD_getBaseline44 = 44;
    private static final int METHOD_getBounds45 = 45;
    private static final int METHOD_getClientProperty46 = 46;
    private static final int METHOD_getComponentAt47 = 47;
    private static final int METHOD_getComponentAt48 = 48;
    private static final int METHOD_getComponentZOrder49 = 49;
    private static final int METHOD_getConditionForKeyStroke50 = 50;
    private static final int METHOD_getDefaultLocale51 = 51;
    private static final int METHOD_getFocusTraversalKeys52 = 52;
    private static final int METHOD_getFontMetrics53 = 53;
    private static final int METHOD_getInsets54 = 54;
    private static final int METHOD_getListeners55 = 55;
    private static final int METHOD_getLocation56 = 56;
    private static final int METHOD_getMousePosition57 = 57;
    private static final int METHOD_getPopupLocation58 = 58;
    private static final int METHOD_getPropertyChangeListeners59 = 59;
    private static final int METHOD_getSize60 = 60;
    private static final int METHOD_getToolTipLocation61 = 61;
    private static final int METHOD_getToolTipText62 = 62;
    private static final int METHOD_gotFocus63 = 63;
    private static final int METHOD_grabFocus64 = 64;
    private static final int METHOD_habilitaBotoesConsulta65 = 65;
    private static final int METHOD_handleEvent66 = 66;
    private static final int METHOD_hasFocus67 = 67;
    private static final int METHOD_hide68 = 68;
    private static final int METHOD_imageUpdate69 = 69;
    private static final int METHOD_insets70 = 70;
    private static final int METHOD_inside71 = 71;
    private static final int METHOD_invalidate72 = 72;
    private static final int METHOD_isAncestorOf73 = 73;
    private static final int METHOD_isFocusCycleRoot74 = 74;
    private static final int METHOD_isFocusOwner75 = 75;
    private static final int METHOD_isLightweightComponent76 = 76;
    private static final int METHOD_keyDown77 = 77;
    private static final int METHOD_keyUp78 = 78;
    private static final int METHOD_layout79 = 79;
    private static final int METHOD_list80 = 80;
    private static final int METHOD_list81 = 81;
    private static final int METHOD_list82 = 82;
    private static final int METHOD_list83 = 83;
    private static final int METHOD_list84 = 84;
    private static final int METHOD_locate85 = 85;
    private static final int METHOD_location86 = 86;
    private static final int METHOD_lostFocus87 = 87;
    private static final int METHOD_minimumSize88 = 88;
    private static final int METHOD_mouseDown89 = 89;
    private static final int METHOD_mouseDrag90 = 90;
    private static final int METHOD_mouseEnter91 = 91;
    private static final int METHOD_mouseExit92 = 92;
    private static final int METHOD_mouseMove93 = 93;
    private static final int METHOD_mouseUp94 = 94;
    private static final int METHOD_move95 = 95;
    private static final int METHOD_moveToBack96 = 96;
    private static final int METHOD_moveToFront97 = 97;
    private static final int METHOD_nextFocus98 = 98;
    private static final int METHOD_pack99 = 99;
    private static final int METHOD_paint100 = 100;
    private static final int METHOD_paintAll101 = 101;
    private static final int METHOD_paintComponents102 = 102;
    private static final int METHOD_paintImmediately103 = 103;
    private static final int METHOD_paintImmediately104 = 104;
    private static final int METHOD_posicaoTela105 = 105;
    private static final int METHOD_postEvent106 = 106;
    private static final int METHOD_preferredSize107 = 107;
    private static final int METHOD_prepareImage108 = 108;
    private static final int METHOD_prepareImage109 = 109;
    private static final int METHOD_print110 = 110;
    private static final int METHOD_printAll111 = 111;
    private static final int METHOD_printComponents112 = 112;
    private static final int METHOD_putClientProperty113 = 113;
    private static final int METHOD_registerKeyboardAction114 = 114;
    private static final int METHOD_registerKeyboardAction115 = 115;
    private static final int METHOD_remove116 = 116;
    private static final int METHOD_remove117 = 117;
    private static final int METHOD_remove118 = 118;
    private static final int METHOD_removeAll119 = 119;
    private static final int METHOD_removeNotify120 = 120;
    private static final int METHOD_removePropertyChangeListener121 = 121;
    private static final int METHOD_repaint122 = 122;
    private static final int METHOD_repaint123 = 123;
    private static final int METHOD_repaint124 = 124;
    private static final int METHOD_repaint125 = 125;
    private static final int METHOD_repaint126 = 126;
    private static final int METHOD_requestDefaultFocus127 = 127;
    private static final int METHOD_requestFocus128 = 128;
    private static final int METHOD_requestFocus129 = 129;
    private static final int METHOD_requestFocusInWindow130 = 130;
    private static final int METHOD_resetKeyboardActions131 = 131;
    private static final int METHOD_reshape132 = 132;
    private static final int METHOD_resize133 = 133;
    private static final int METHOD_resize134 = 134;
    private static final int METHOD_restoreSubcomponentFocus135 = 135;
    private static final int METHOD_revalidate136 = 136;
    private static final int METHOD_scrollRectToVisible137 = 137;
    private static final int METHOD_setBounds138 = 138;
    private static final int METHOD_setComponentZOrder139 = 139;
    private static final int METHOD_setDefaultLocale140 = 140;
    private static final int METHOD_setLayer141 = 141;
    private static final int METHOD_show142 = 142;
    private static final int METHOD_show143 = 143;
    private static final int METHOD_size144 = 144;
    private static final int METHOD_toBack145 = 145;
    private static final int METHOD_toFront146 = 146;
    private static final int METHOD_toString147 = 147;
    private static final int METHOD_transferFocus148 = 148;
    private static final int METHOD_transferFocusBackward149 = 149;
    private static final int METHOD_transferFocusDownCycle150 = 150;
    private static final int METHOD_transferFocusUpCycle151 = 151;
    private static final int METHOD_unregisterKeyboardAction152 = 152;
    private static final int METHOD_update153 = 153;
    private static final int METHOD_updateUI154 = 154;
    private static final int METHOD_validate155 = 155;

    // Method array 
    /*lazy MethodDescriptor*/
    private static MethodDescriptor[] getMdescriptor(){
        MethodDescriptor[] methods = new MethodDescriptor[156];
    
        try {
            methods[METHOD_action0] = new MethodDescriptor(java.awt.Component.class.getMethod("action", new Class[] {java.awt.Event.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_action0].setDisplayName ( "" );
            methods[METHOD_add1] = new MethodDescriptor(java.awt.Component.class.getMethod("add", new Class[] {java.awt.PopupMenu.class})); // NOI18N
            methods[METHOD_add1].setDisplayName ( "" );
            methods[METHOD_add2] = new MethodDescriptor(java.awt.Container.class.getMethod("add", new Class[] {java.awt.Component.class})); // NOI18N
            methods[METHOD_add2].setDisplayName ( "" );
            methods[METHOD_add3] = new MethodDescriptor(java.awt.Container.class.getMethod("add", new Class[] {java.lang.String.class, java.awt.Component.class})); // NOI18N
            methods[METHOD_add3].setDisplayName ( "" );
            methods[METHOD_add4] = new MethodDescriptor(java.awt.Container.class.getMethod("add", new Class[] {java.awt.Component.class, int.class})); // NOI18N
            methods[METHOD_add4].setDisplayName ( "" );
            methods[METHOD_add5] = new MethodDescriptor(java.awt.Container.class.getMethod("add", new Class[] {java.awt.Component.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_add5].setDisplayName ( "" );
            methods[METHOD_add6] = new MethodDescriptor(java.awt.Container.class.getMethod("add", new Class[] {java.awt.Component.class, java.lang.Object.class, int.class})); // NOI18N
            methods[METHOD_add6].setDisplayName ( "" );
            methods[METHOD_addNotify7] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("addNotify", new Class[] {})); // NOI18N
            methods[METHOD_addNotify7].setDisplayName ( "" );
            methods[METHOD_addPropertyChangeListener8] = new MethodDescriptor(java.awt.Container.class.getMethod("addPropertyChangeListener", new Class[] {java.lang.String.class, java.beans.PropertyChangeListener.class})); // NOI18N
            methods[METHOD_addPropertyChangeListener8].setDisplayName ( "" );
            methods[METHOD_applyComponentOrientation9] = new MethodDescriptor(java.awt.Container.class.getMethod("applyComponentOrientation", new Class[] {java.awt.ComponentOrientation.class})); // NOI18N
            methods[METHOD_applyComponentOrientation9].setDisplayName ( "" );
            methods[METHOD_areFocusTraversalKeysSet10] = new MethodDescriptor(java.awt.Container.class.getMethod("areFocusTraversalKeysSet", new Class[] {int.class})); // NOI18N
            methods[METHOD_areFocusTraversalKeysSet10].setDisplayName ( "" );
            methods[METHOD_bounds11] = new MethodDescriptor(java.awt.Component.class.getMethod("bounds", new Class[] {})); // NOI18N
            methods[METHOD_bounds11].setDisplayName ( "" );
            methods[METHOD_checkImage12] = new MethodDescriptor(java.awt.Component.class.getMethod("checkImage", new Class[] {java.awt.Image.class, java.awt.image.ImageObserver.class})); // NOI18N
            methods[METHOD_checkImage12].setDisplayName ( "" );
            methods[METHOD_checkImage13] = new MethodDescriptor(java.awt.Component.class.getMethod("checkImage", new Class[] {java.awt.Image.class, int.class, int.class, java.awt.image.ImageObserver.class})); // NOI18N
            methods[METHOD_checkImage13].setDisplayName ( "" );
            methods[METHOD_computeVisibleRect14] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("computeVisibleRect", new Class[] {java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_computeVisibleRect14].setDisplayName ( "" );
            methods[METHOD_consultarDados15] = new MethodDescriptor(telaMovimenacao.TelaRecPag.class.getMethod("consultarDados", new Class[] {int.class})); // NOI18N
            methods[METHOD_consultarDados15].setDisplayName ( "" );
            methods[METHOD_contains16] = new MethodDescriptor(java.awt.Component.class.getMethod("contains", new Class[] {java.awt.Point.class})); // NOI18N
            methods[METHOD_contains16].setDisplayName ( "" );
            methods[METHOD_contains17] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("contains", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_contains17].setDisplayName ( "" );
            methods[METHOD_countComponents18] = new MethodDescriptor(java.awt.Container.class.getMethod("countComponents", new Class[] {})); // NOI18N
            methods[METHOD_countComponents18].setDisplayName ( "" );
            methods[METHOD_createImage19] = new MethodDescriptor(java.awt.Component.class.getMethod("createImage", new Class[] {java.awt.image.ImageProducer.class})); // NOI18N
            methods[METHOD_createImage19].setDisplayName ( "" );
            methods[METHOD_createImage20] = new MethodDescriptor(java.awt.Component.class.getMethod("createImage", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_createImage20].setDisplayName ( "" );
            methods[METHOD_createToolTip21] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("createToolTip", new Class[] {})); // NOI18N
            methods[METHOD_createToolTip21].setDisplayName ( "" );
            methods[METHOD_createVolatileImage22] = new MethodDescriptor(java.awt.Component.class.getMethod("createVolatileImage", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_createVolatileImage22].setDisplayName ( "" );
            methods[METHOD_createVolatileImage23] = new MethodDescriptor(java.awt.Component.class.getMethod("createVolatileImage", new Class[] {int.class, int.class, java.awt.ImageCapabilities.class})); // NOI18N
            methods[METHOD_createVolatileImage23].setDisplayName ( "" );
            methods[METHOD_deliverEvent24] = new MethodDescriptor(java.awt.Container.class.getMethod("deliverEvent", new Class[] {java.awt.Event.class})); // NOI18N
            methods[METHOD_deliverEvent24].setDisplayName ( "" );
            methods[METHOD_disable25] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("disable", new Class[] {})); // NOI18N
            methods[METHOD_disable25].setDisplayName ( "" );
            methods[METHOD_dispatchEvent26] = new MethodDescriptor(java.awt.Component.class.getMethod("dispatchEvent", new Class[] {java.awt.AWTEvent.class})); // NOI18N
            methods[METHOD_dispatchEvent26].setDisplayName ( "" );
            methods[METHOD_dispose27] = new MethodDescriptor(javax.swing.JInternalFrame.class.getMethod("dispose", new Class[] {})); // NOI18N
            methods[METHOD_dispose27].setDisplayName ( "" );
            methods[METHOD_doDefaultCloseAction28] = new MethodDescriptor(javax.swing.JInternalFrame.class.getMethod("doDefaultCloseAction", new Class[] {})); // NOI18N
            methods[METHOD_doDefaultCloseAction28].setDisplayName ( "" );
            methods[METHOD_doLayout29] = new MethodDescriptor(java.awt.Container.class.getMethod("doLayout", new Class[] {})); // NOI18N
            methods[METHOD_doLayout29].setDisplayName ( "" );
            methods[METHOD_enable30] = new MethodDescriptor(java.awt.Component.class.getMethod("enable", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_enable30].setDisplayName ( "" );
            methods[METHOD_enable31] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("enable", new Class[] {})); // NOI18N
            methods[METHOD_enable31].setDisplayName ( "" );
            methods[METHOD_enableInputMethods32] = new MethodDescriptor(java.awt.Component.class.getMethod("enableInputMethods", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_enableInputMethods32].setDisplayName ( "" );
            methods[METHOD_findComponentAt33] = new MethodDescriptor(java.awt.Container.class.getMethod("findComponentAt", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_findComponentAt33].setDisplayName ( "" );
            methods[METHOD_findComponentAt34] = new MethodDescriptor(java.awt.Container.class.getMethod("findComponentAt", new Class[] {java.awt.Point.class})); // NOI18N
            methods[METHOD_findComponentAt34].setDisplayName ( "" );
            methods[METHOD_firePropertyChange35] = new MethodDescriptor(java.awt.Component.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, byte.class, byte.class})); // NOI18N
            methods[METHOD_firePropertyChange35].setDisplayName ( "" );
            methods[METHOD_firePropertyChange36] = new MethodDescriptor(java.awt.Component.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, short.class, short.class})); // NOI18N
            methods[METHOD_firePropertyChange36].setDisplayName ( "" );
            methods[METHOD_firePropertyChange37] = new MethodDescriptor(java.awt.Component.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, long.class, long.class})); // NOI18N
            methods[METHOD_firePropertyChange37].setDisplayName ( "" );
            methods[METHOD_firePropertyChange38] = new MethodDescriptor(java.awt.Component.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, float.class, float.class})); // NOI18N
            methods[METHOD_firePropertyChange38].setDisplayName ( "" );
            methods[METHOD_firePropertyChange39] = new MethodDescriptor(java.awt.Component.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, double.class, double.class})); // NOI18N
            methods[METHOD_firePropertyChange39].setDisplayName ( "" );
            methods[METHOD_firePropertyChange40] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, boolean.class, boolean.class})); // NOI18N
            methods[METHOD_firePropertyChange40].setDisplayName ( "" );
            methods[METHOD_firePropertyChange41] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, int.class, int.class})); // NOI18N
            methods[METHOD_firePropertyChange41].setDisplayName ( "" );
            methods[METHOD_firePropertyChange42] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, char.class, char.class})); // NOI18N
            methods[METHOD_firePropertyChange42].setDisplayName ( "" );
            methods[METHOD_getActionForKeyStroke43] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getActionForKeyStroke", new Class[] {javax.swing.KeyStroke.class})); // NOI18N
            methods[METHOD_getActionForKeyStroke43].setDisplayName ( "" );
            methods[METHOD_getBaseline44] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getBaseline", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_getBaseline44].setDisplayName ( "" );
            methods[METHOD_getBounds45] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getBounds", new Class[] {java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_getBounds45].setDisplayName ( "" );
            methods[METHOD_getClientProperty46] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getClientProperty", new Class[] {java.lang.Object.class})); // NOI18N
            methods[METHOD_getClientProperty46].setDisplayName ( "" );
            methods[METHOD_getComponentAt47] = new MethodDescriptor(java.awt.Container.class.getMethod("getComponentAt", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_getComponentAt47].setDisplayName ( "" );
            methods[METHOD_getComponentAt48] = new MethodDescriptor(java.awt.Container.class.getMethod("getComponentAt", new Class[] {java.awt.Point.class})); // NOI18N
            methods[METHOD_getComponentAt48].setDisplayName ( "" );
            methods[METHOD_getComponentZOrder49] = new MethodDescriptor(java.awt.Container.class.getMethod("getComponentZOrder", new Class[] {java.awt.Component.class})); // NOI18N
            methods[METHOD_getComponentZOrder49].setDisplayName ( "" );
            methods[METHOD_getConditionForKeyStroke50] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getConditionForKeyStroke", new Class[] {javax.swing.KeyStroke.class})); // NOI18N
            methods[METHOD_getConditionForKeyStroke50].setDisplayName ( "" );
            methods[METHOD_getDefaultLocale51] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getDefaultLocale", new Class[] {})); // NOI18N
            methods[METHOD_getDefaultLocale51].setDisplayName ( "" );
            methods[METHOD_getFocusTraversalKeys52] = new MethodDescriptor(java.awt.Container.class.getMethod("getFocusTraversalKeys", new Class[] {int.class})); // NOI18N
            methods[METHOD_getFocusTraversalKeys52].setDisplayName ( "" );
            methods[METHOD_getFontMetrics53] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getFontMetrics", new Class[] {java.awt.Font.class})); // NOI18N
            methods[METHOD_getFontMetrics53].setDisplayName ( "" );
            methods[METHOD_getInsets54] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getInsets", new Class[] {java.awt.Insets.class})); // NOI18N
            methods[METHOD_getInsets54].setDisplayName ( "" );
            methods[METHOD_getListeners55] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getListeners", new Class[] {java.lang.Class.class})); // NOI18N
            methods[METHOD_getListeners55].setDisplayName ( "" );
            methods[METHOD_getLocation56] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getLocation", new Class[] {java.awt.Point.class})); // NOI18N
            methods[METHOD_getLocation56].setDisplayName ( "" );
            methods[METHOD_getMousePosition57] = new MethodDescriptor(java.awt.Container.class.getMethod("getMousePosition", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_getMousePosition57].setDisplayName ( "" );
            methods[METHOD_getPopupLocation58] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getPopupLocation", new Class[] {java.awt.event.MouseEvent.class})); // NOI18N
            methods[METHOD_getPopupLocation58].setDisplayName ( "" );
            methods[METHOD_getPropertyChangeListeners59] = new MethodDescriptor(java.awt.Component.class.getMethod("getPropertyChangeListeners", new Class[] {java.lang.String.class})); // NOI18N
            methods[METHOD_getPropertyChangeListeners59].setDisplayName ( "" );
            methods[METHOD_getSize60] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getSize", new Class[] {java.awt.Dimension.class})); // NOI18N
            methods[METHOD_getSize60].setDisplayName ( "" );
            methods[METHOD_getToolTipLocation61] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getToolTipLocation", new Class[] {java.awt.event.MouseEvent.class})); // NOI18N
            methods[METHOD_getToolTipLocation61].setDisplayName ( "" );
            methods[METHOD_getToolTipText62] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getToolTipText", new Class[] {java.awt.event.MouseEvent.class})); // NOI18N
            methods[METHOD_getToolTipText62].setDisplayName ( "" );
            methods[METHOD_gotFocus63] = new MethodDescriptor(java.awt.Component.class.getMethod("gotFocus", new Class[] {java.awt.Event.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_gotFocus63].setDisplayName ( "" );
            methods[METHOD_grabFocus64] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("grabFocus", new Class[] {})); // NOI18N
            methods[METHOD_grabFocus64].setDisplayName ( "" );
            methods[METHOD_habilitaBotoesConsulta65] = new MethodDescriptor(telaMovimenacao.TelaRecPag.class.getMethod("habilitaBotoesConsulta", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_habilitaBotoesConsulta65].setDisplayName ( "" );
            methods[METHOD_handleEvent66] = new MethodDescriptor(java.awt.Component.class.getMethod("handleEvent", new Class[] {java.awt.Event.class})); // NOI18N
            methods[METHOD_handleEvent66].setDisplayName ( "" );
            methods[METHOD_hasFocus67] = new MethodDescriptor(java.awt.Component.class.getMethod("hasFocus", new Class[] {})); // NOI18N
            methods[METHOD_hasFocus67].setDisplayName ( "" );
            methods[METHOD_hide68] = new MethodDescriptor(javax.swing.JInternalFrame.class.getMethod("hide", new Class[] {})); // NOI18N
            methods[METHOD_hide68].setDisplayName ( "" );
            methods[METHOD_imageUpdate69] = new MethodDescriptor(java.awt.Component.class.getMethod("imageUpdate", new Class[] {java.awt.Image.class, int.class, int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_imageUpdate69].setDisplayName ( "" );
            methods[METHOD_insets70] = new MethodDescriptor(java.awt.Container.class.getMethod("insets", new Class[] {})); // NOI18N
            methods[METHOD_insets70].setDisplayName ( "" );
            methods[METHOD_inside71] = new MethodDescriptor(java.awt.Component.class.getMethod("inside", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_inside71].setDisplayName ( "" );
            methods[METHOD_invalidate72] = new MethodDescriptor(java.awt.Container.class.getMethod("invalidate", new Class[] {})); // NOI18N
            methods[METHOD_invalidate72].setDisplayName ( "" );
            methods[METHOD_isAncestorOf73] = new MethodDescriptor(java.awt.Container.class.getMethod("isAncestorOf", new Class[] {java.awt.Component.class})); // NOI18N
            methods[METHOD_isAncestorOf73].setDisplayName ( "" );
            methods[METHOD_isFocusCycleRoot74] = new MethodDescriptor(java.awt.Container.class.getMethod("isFocusCycleRoot", new Class[] {java.awt.Container.class})); // NOI18N
            methods[METHOD_isFocusCycleRoot74].setDisplayName ( "" );
            methods[METHOD_isFocusOwner75] = new MethodDescriptor(java.awt.Component.class.getMethod("isFocusOwner", new Class[] {})); // NOI18N
            methods[METHOD_isFocusOwner75].setDisplayName ( "" );
            methods[METHOD_isLightweightComponent76] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("isLightweightComponent", new Class[] {java.awt.Component.class})); // NOI18N
            methods[METHOD_isLightweightComponent76].setDisplayName ( "" );
            methods[METHOD_keyDown77] = new MethodDescriptor(java.awt.Component.class.getMethod("keyDown", new Class[] {java.awt.Event.class, int.class})); // NOI18N
            methods[METHOD_keyDown77].setDisplayName ( "" );
            methods[METHOD_keyUp78] = new MethodDescriptor(java.awt.Component.class.getMethod("keyUp", new Class[] {java.awt.Event.class, int.class})); // NOI18N
            methods[METHOD_keyUp78].setDisplayName ( "" );
            methods[METHOD_layout79] = new MethodDescriptor(java.awt.Container.class.getMethod("layout", new Class[] {})); // NOI18N
            methods[METHOD_layout79].setDisplayName ( "" );
            methods[METHOD_list80] = new MethodDescriptor(java.awt.Component.class.getMethod("list", new Class[] {})); // NOI18N
            methods[METHOD_list80].setDisplayName ( "" );
            methods[METHOD_list81] = new MethodDescriptor(java.awt.Component.class.getMethod("list", new Class[] {java.io.PrintStream.class})); // NOI18N
            methods[METHOD_list81].setDisplayName ( "" );
            methods[METHOD_list82] = new MethodDescriptor(java.awt.Component.class.getMethod("list", new Class[] {java.io.PrintWriter.class})); // NOI18N
            methods[METHOD_list82].setDisplayName ( "" );
            methods[METHOD_list83] = new MethodDescriptor(java.awt.Container.class.getMethod("list", new Class[] {java.io.PrintStream.class, int.class})); // NOI18N
            methods[METHOD_list83].setDisplayName ( "" );
            methods[METHOD_list84] = new MethodDescriptor(java.awt.Container.class.getMethod("list", new Class[] {java.io.PrintWriter.class, int.class})); // NOI18N
            methods[METHOD_list84].setDisplayName ( "" );
            methods[METHOD_locate85] = new MethodDescriptor(java.awt.Container.class.getMethod("locate", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_locate85].setDisplayName ( "" );
            methods[METHOD_location86] = new MethodDescriptor(java.awt.Component.class.getMethod("location", new Class[] {})); // NOI18N
            methods[METHOD_location86].setDisplayName ( "" );
            methods[METHOD_lostFocus87] = new MethodDescriptor(java.awt.Component.class.getMethod("lostFocus", new Class[] {java.awt.Event.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_lostFocus87].setDisplayName ( "" );
            methods[METHOD_minimumSize88] = new MethodDescriptor(java.awt.Container.class.getMethod("minimumSize", new Class[] {})); // NOI18N
            methods[METHOD_minimumSize88].setDisplayName ( "" );
            methods[METHOD_mouseDown89] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseDown", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseDown89].setDisplayName ( "" );
            methods[METHOD_mouseDrag90] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseDrag", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseDrag90].setDisplayName ( "" );
            methods[METHOD_mouseEnter91] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseEnter", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseEnter91].setDisplayName ( "" );
            methods[METHOD_mouseExit92] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseExit", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseExit92].setDisplayName ( "" );
            methods[METHOD_mouseMove93] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseMove", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseMove93].setDisplayName ( "" );
            methods[METHOD_mouseUp94] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseUp", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseUp94].setDisplayName ( "" );
            methods[METHOD_move95] = new MethodDescriptor(java.awt.Component.class.getMethod("move", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_move95].setDisplayName ( "" );
            methods[METHOD_moveToBack96] = new MethodDescriptor(javax.swing.JInternalFrame.class.getMethod("moveToBack", new Class[] {})); // NOI18N
            methods[METHOD_moveToBack96].setDisplayName ( "" );
            methods[METHOD_moveToFront97] = new MethodDescriptor(javax.swing.JInternalFrame.class.getMethod("moveToFront", new Class[] {})); // NOI18N
            methods[METHOD_moveToFront97].setDisplayName ( "" );
            methods[METHOD_nextFocus98] = new MethodDescriptor(java.awt.Component.class.getMethod("nextFocus", new Class[] {})); // NOI18N
            methods[METHOD_nextFocus98].setDisplayName ( "" );
            methods[METHOD_pack99] = new MethodDescriptor(javax.swing.JInternalFrame.class.getMethod("pack", new Class[] {})); // NOI18N
            methods[METHOD_pack99].setDisplayName ( "" );
            methods[METHOD_paint100] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("paint", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_paint100].setDisplayName ( "" );
            methods[METHOD_paintAll101] = new MethodDescriptor(java.awt.Component.class.getMethod("paintAll", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_paintAll101].setDisplayName ( "" );
            methods[METHOD_paintComponents102] = new MethodDescriptor(java.awt.Container.class.getMethod("paintComponents", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_paintComponents102].setDisplayName ( "" );
            methods[METHOD_paintImmediately103] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("paintImmediately", new Class[] {int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_paintImmediately103].setDisplayName ( "" );
            methods[METHOD_paintImmediately104] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("paintImmediately", new Class[] {java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_paintImmediately104].setDisplayName ( "" );
            methods[METHOD_posicaoTela105] = new MethodDescriptor(telaMovimenacao.TelaRecPag.class.getMethod("posicaoTela", new Class[] {})); // NOI18N
            methods[METHOD_posicaoTela105].setDisplayName ( "" );
            methods[METHOD_postEvent106] = new MethodDescriptor(java.awt.Component.class.getMethod("postEvent", new Class[] {java.awt.Event.class})); // NOI18N
            methods[METHOD_postEvent106].setDisplayName ( "" );
            methods[METHOD_preferredSize107] = new MethodDescriptor(java.awt.Container.class.getMethod("preferredSize", new Class[] {})); // NOI18N
            methods[METHOD_preferredSize107].setDisplayName ( "" );
            methods[METHOD_prepareImage108] = new MethodDescriptor(java.awt.Component.class.getMethod("prepareImage", new Class[] {java.awt.Image.class, java.awt.image.ImageObserver.class})); // NOI18N
            methods[METHOD_prepareImage108].setDisplayName ( "" );
            methods[METHOD_prepareImage109] = new MethodDescriptor(java.awt.Component.class.getMethod("prepareImage", new Class[] {java.awt.Image.class, int.class, int.class, java.awt.image.ImageObserver.class})); // NOI18N
            methods[METHOD_prepareImage109].setDisplayName ( "" );
            methods[METHOD_print110] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("print", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_print110].setDisplayName ( "" );
            methods[METHOD_printAll111] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("printAll", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_printAll111].setDisplayName ( "" );
            methods[METHOD_printComponents112] = new MethodDescriptor(java.awt.Container.class.getMethod("printComponents", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_printComponents112].setDisplayName ( "" );
            methods[METHOD_putClientProperty113] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("putClientProperty", new Class[] {java.lang.Object.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_putClientProperty113].setDisplayName ( "" );
            methods[METHOD_registerKeyboardAction114] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("registerKeyboardAction", new Class[] {java.awt.event.ActionListener.class, java.lang.String.class, javax.swing.KeyStroke.class, int.class})); // NOI18N
            methods[METHOD_registerKeyboardAction114].setDisplayName ( "" );
            methods[METHOD_registerKeyboardAction115] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("registerKeyboardAction", new Class[] {java.awt.event.ActionListener.class, javax.swing.KeyStroke.class, int.class})); // NOI18N
            methods[METHOD_registerKeyboardAction115].setDisplayName ( "" );
            methods[METHOD_remove116] = new MethodDescriptor(java.awt.Component.class.getMethod("remove", new Class[] {java.awt.MenuComponent.class})); // NOI18N
            methods[METHOD_remove116].setDisplayName ( "" );
            methods[METHOD_remove117] = new MethodDescriptor(java.awt.Container.class.getMethod("remove", new Class[] {int.class})); // NOI18N
            methods[METHOD_remove117].setDisplayName ( "" );
            methods[METHOD_remove118] = new MethodDescriptor(javax.swing.JInternalFrame.class.getMethod("remove", new Class[] {java.awt.Component.class})); // NOI18N
            methods[METHOD_remove118].setDisplayName ( "" );
            methods[METHOD_removeAll119] = new MethodDescriptor(java.awt.Container.class.getMethod("removeAll", new Class[] {})); // NOI18N
            methods[METHOD_removeAll119].setDisplayName ( "" );
            methods[METHOD_removeNotify120] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("removeNotify", new Class[] {})); // NOI18N
            methods[METHOD_removeNotify120].setDisplayName ( "" );
            methods[METHOD_removePropertyChangeListener121] = new MethodDescriptor(java.awt.Component.class.getMethod("removePropertyChangeListener", new Class[] {java.lang.String.class, java.beans.PropertyChangeListener.class})); // NOI18N
            methods[METHOD_removePropertyChangeListener121].setDisplayName ( "" );
            methods[METHOD_repaint122] = new MethodDescriptor(java.awt.Component.class.getMethod("repaint", new Class[] {})); // NOI18N
            methods[METHOD_repaint122].setDisplayName ( "" );
            methods[METHOD_repaint123] = new MethodDescriptor(java.awt.Component.class.getMethod("repaint", new Class[] {long.class})); // NOI18N
            methods[METHOD_repaint123].setDisplayName ( "" );
            methods[METHOD_repaint124] = new MethodDescriptor(java.awt.Component.class.getMethod("repaint", new Class[] {int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_repaint124].setDisplayName ( "" );
            methods[METHOD_repaint125] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("repaint", new Class[] {long.class, int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_repaint125].setDisplayName ( "" );
            methods[METHOD_repaint126] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("repaint", new Class[] {java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_repaint126].setDisplayName ( "" );
            methods[METHOD_requestDefaultFocus127] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("requestDefaultFocus", new Class[] {})); // NOI18N
            methods[METHOD_requestDefaultFocus127].setDisplayName ( "" );
            methods[METHOD_requestFocus128] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("requestFocus", new Class[] {})); // NOI18N
            methods[METHOD_requestFocus128].setDisplayName ( "" );
            methods[METHOD_requestFocus129] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("requestFocus", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_requestFocus129].setDisplayName ( "" );
            methods[METHOD_requestFocusInWindow130] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("requestFocusInWindow", new Class[] {})); // NOI18N
            methods[METHOD_requestFocusInWindow130].setDisplayName ( "" );
            methods[METHOD_resetKeyboardActions131] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("resetKeyboardActions", new Class[] {})); // NOI18N
            methods[METHOD_resetKeyboardActions131].setDisplayName ( "" );
            methods[METHOD_reshape132] = new MethodDescriptor(javax.swing.JInternalFrame.class.getMethod("reshape", new Class[] {int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_reshape132].setDisplayName ( "" );
            methods[METHOD_resize133] = new MethodDescriptor(java.awt.Component.class.getMethod("resize", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_resize133].setDisplayName ( "" );
            methods[METHOD_resize134] = new MethodDescriptor(java.awt.Component.class.getMethod("resize", new Class[] {java.awt.Dimension.class})); // NOI18N
            methods[METHOD_resize134].setDisplayName ( "" );
            methods[METHOD_restoreSubcomponentFocus135] = new MethodDescriptor(javax.swing.JInternalFrame.class.getMethod("restoreSubcomponentFocus", new Class[] {})); // NOI18N
            methods[METHOD_restoreSubcomponentFocus135].setDisplayName ( "" );
            methods[METHOD_revalidate136] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("revalidate", new Class[] {})); // NOI18N
            methods[METHOD_revalidate136].setDisplayName ( "" );
            methods[METHOD_scrollRectToVisible137] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("scrollRectToVisible", new Class[] {java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_scrollRectToVisible137].setDisplayName ( "" );
            methods[METHOD_setBounds138] = new MethodDescriptor(java.awt.Component.class.getMethod("setBounds", new Class[] {int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_setBounds138].setDisplayName ( "" );
            methods[METHOD_setComponentZOrder139] = new MethodDescriptor(java.awt.Container.class.getMethod("setComponentZOrder", new Class[] {java.awt.Component.class, int.class})); // NOI18N
            methods[METHOD_setComponentZOrder139].setDisplayName ( "" );
            methods[METHOD_setDefaultLocale140] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("setDefaultLocale", new Class[] {java.util.Locale.class})); // NOI18N
            methods[METHOD_setDefaultLocale140].setDisplayName ( "" );
            methods[METHOD_setLayer141] = new MethodDescriptor(javax.swing.JInternalFrame.class.getMethod("setLayer", new Class[] {java.lang.Integer.class})); // NOI18N
            methods[METHOD_setLayer141].setDisplayName ( "" );
            methods[METHOD_show142] = new MethodDescriptor(java.awt.Component.class.getMethod("show", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_show142].setDisplayName ( "" );
            methods[METHOD_show143] = new MethodDescriptor(javax.swing.JInternalFrame.class.getMethod("show", new Class[] {})); // NOI18N
            methods[METHOD_show143].setDisplayName ( "" );
            methods[METHOD_size144] = new MethodDescriptor(java.awt.Component.class.getMethod("size", new Class[] {})); // NOI18N
            methods[METHOD_size144].setDisplayName ( "" );
            methods[METHOD_toBack145] = new MethodDescriptor(javax.swing.JInternalFrame.class.getMethod("toBack", new Class[] {})); // NOI18N
            methods[METHOD_toBack145].setDisplayName ( "" );
            methods[METHOD_toFront146] = new MethodDescriptor(javax.swing.JInternalFrame.class.getMethod("toFront", new Class[] {})); // NOI18N
            methods[METHOD_toFront146].setDisplayName ( "" );
            methods[METHOD_toString147] = new MethodDescriptor(java.awt.Component.class.getMethod("toString", new Class[] {})); // NOI18N
            methods[METHOD_toString147].setDisplayName ( "" );
            methods[METHOD_transferFocus148] = new MethodDescriptor(java.awt.Component.class.getMethod("transferFocus", new Class[] {})); // NOI18N
            methods[METHOD_transferFocus148].setDisplayName ( "" );
            methods[METHOD_transferFocusBackward149] = new MethodDescriptor(java.awt.Container.class.getMethod("transferFocusBackward", new Class[] {})); // NOI18N
            methods[METHOD_transferFocusBackward149].setDisplayName ( "" );
            methods[METHOD_transferFocusDownCycle150] = new MethodDescriptor(java.awt.Container.class.getMethod("transferFocusDownCycle", new Class[] {})); // NOI18N
            methods[METHOD_transferFocusDownCycle150].setDisplayName ( "" );
            methods[METHOD_transferFocusUpCycle151] = new MethodDescriptor(java.awt.Component.class.getMethod("transferFocusUpCycle", new Class[] {})); // NOI18N
            methods[METHOD_transferFocusUpCycle151].setDisplayName ( "" );
            methods[METHOD_unregisterKeyboardAction152] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("unregisterKeyboardAction", new Class[] {javax.swing.KeyStroke.class})); // NOI18N
            methods[METHOD_unregisterKeyboardAction152].setDisplayName ( "" );
            methods[METHOD_update153] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("update", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_update153].setDisplayName ( "" );
            methods[METHOD_updateUI154] = new MethodDescriptor(javax.swing.JInternalFrame.class.getMethod("updateUI", new Class[] {})); // NOI18N
            methods[METHOD_updateUI154].setDisplayName ( "" );
            methods[METHOD_validate155] = new MethodDescriptor(java.awt.Container.class.getMethod("validate", new Class[] {})); // NOI18N
            methods[METHOD_validate155].setDisplayName ( "" );
        }
        catch( Exception e) {}//GEN-HEADEREND:Methods

        // Here you can add code for customizing the methods array.

        return methods;     }//GEN-LAST:Methods
    private static java.awt.Image iconColor16 = null;//GEN-BEGIN:IconsDef
    private static java.awt.Image iconColor32 = null;
    private static java.awt.Image iconMono16 = null;
    private static java.awt.Image iconMono32 = null;//GEN-END:IconsDef
    private static String iconNameC16 = null;//GEN-BEGIN:Icons
    private static String iconNameC32 = null;
    private static String iconNameM16 = null;
    private static String iconNameM32 = null;//GEN-END:Icons
    private static final int defaultPropertyIndex = -1;//GEN-BEGIN:Idx
    private static final int defaultEventIndex = -1;//GEN-END:Idx

//GEN-FIRST:Superclass
    // Here you can add code for customizing the Superclass BeanInfo.
//GEN-LAST:Superclass
    /**
     * Gets the bean's <code>BeanDescriptor</code>s.
     * 
     * @return BeanDescriptor describing the editable
     * properties of this bean.  May return null if the
     * information should be obtained by automatic analysis.
     */
    public BeanDescriptor getBeanDescriptor() {
        return getBdescriptor();
    }

    /**
     * Gets the bean's <code>PropertyDescriptor</code>s.
     * 
     * @return An array of PropertyDescriptors describing the editable
     * properties supported by this bean.  May return null if the
     * information should be obtained by automatic analysis.
     * <p>
     * If a property is indexed, then its entry in the result array will
     * belong to the IndexedPropertyDescriptor subclass of PropertyDescriptor.
     * A client of getPropertyDescriptors can use "instanceof" to check
     * if a given PropertyDescriptor is an IndexedPropertyDescriptor.
     */
    public PropertyDescriptor[] getPropertyDescriptors() {
        return getPdescriptor();
    }

    /**
     * Gets the bean's <code>EventSetDescriptor</code>s.
     * 
     * @return  An array of EventSetDescriptors describing the kinds of 
     * events fired by this bean.  May return null if the information
     * should be obtained by automatic analysis.
     */
    public EventSetDescriptor[] getEventSetDescriptors() {
        return getEdescriptor();
    }

    /**
     * Gets the bean's <code>MethodDescriptor</code>s.
     * 
     * @return  An array of MethodDescriptors describing the methods 
     * implemented by this bean.  May return null if the information
     * should be obtained by automatic analysis.
     */
    public MethodDescriptor[] getMethodDescriptors() {
        return getMdescriptor();
    }

    /**
     * A bean may have a "default" property that is the property that will
     * mostly commonly be initially chosen for update by human's who are 
     * customizing the bean.
     * @return  Index of default property in the PropertyDescriptor array
     * 		returned by getPropertyDescriptors.
     * <P>	Returns -1 if there is no default property.
     */
    public int getDefaultPropertyIndex() {
        return defaultPropertyIndex;
    }

    /**
     * A bean may have a "default" event that is the event that will
     * mostly commonly be used by human's when using the bean. 
     * @return Index of default event in the EventSetDescriptor array
     *		returned by getEventSetDescriptors.
     * <P>	Returns -1 if there is no default event.
     */
    public int getDefaultEventIndex() {
        return defaultEventIndex;
    }

    /**
     * This method returns an image object that can be used to
     * represent the bean in toolboxes, toolbars, etc.   Icon images
     * will typically be GIFs, but may in future include other formats.
     * <p>
     * Beans aren't required to provide icons and may return null from
     * this method.
     * <p>
     * There are four possible flavors of icons (16x16 color,
     * 32x32 color, 16x16 mono, 32x32 mono).  If a bean choses to only
     * support a single icon we recommend supporting 16x16 color.
     * <p>
     * We recommend that icons have a "transparent" background
     * so they can be rendered onto an existing background.
     *
     * @param  iconKind  The kind of icon requested.  This should be
     *    one of the constant values ICON_COLOR_16x16, ICON_COLOR_32x32, 
     *    ICON_MONO_16x16, or ICON_MONO_32x32.
     * @return  An image object representing the requested icon.  May
     *    return null if no suitable icon is available.
     */
    public java.awt.Image getIcon(int iconKind) {
        switch (iconKind) {
            case ICON_COLOR_16x16:
                if (iconNameC16 == null) {
                    return null;
                } else {
                    if (iconColor16 == null) {
                        iconColor16 = loadImage(iconNameC16);
                    }
                    return iconColor16;
                }
            case ICON_COLOR_32x32:
                if (iconNameC32 == null) {
                    return null;
                } else {
                    if (iconColor32 == null) {
                        iconColor32 = loadImage(iconNameC32);
                    }
                    return iconColor32;
                }
            case ICON_MONO_16x16:
                if (iconNameM16 == null) {
                    return null;
                } else {
                    if (iconMono16 == null) {
                        iconMono16 = loadImage(iconNameM16);
                    }
                    return iconMono16;
                }
            case ICON_MONO_32x32:
                if (iconNameM32 == null) {
                    return null;
                } else {
                    if (iconMono32 == null) {
                        iconMono32 = loadImage(iconNameM32);
                    }
                    return iconMono32;
                }
            default:
                return null;
        }
    }
}
