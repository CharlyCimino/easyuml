package org.uml.visual.widgets.members;

import org.uml.memberparser.MemberParser;
import java.awt.font.TextAttribute;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import org.netbeans.api.visual.action.ActionFactory;
import org.uml.model.members.Method;
import org.uml.visual.widgets.ClassDiagramScene;
import org.uml.visual.widgets.ISignedUMLWidget;
import org.uml.visual.widgets.actions.MemberNameEditor;

/**
 *
 * @author Jelena
 */
public class MethodWidget extends MemberWidgetBase implements ISignedUMLWidget {

    public MethodWidget(ClassDiagramScene scene, Method method) {
        super(scene, method);

        updateIcon();
        this.addChild(iconWidget);

        updateVisibilityLabel();
        this.addChild(visibilityLabel);

        nameLabel.setLabel(method.getLabelText(getClassDiagramScene().isShowSimpleTypes()));
        nameLabel.getActions().addAction(ActionFactory.createInplaceEditorAction(new MemberNameEditor(this)));
        nameLabel.setFont(scene.getFont());
        setFontStyle(method.isStatic(), method.isAbstract());
        this.addChild(nameLabel);
        setChildConstraint(nameLabel, 1);   // any number, as it defines weight by which it will occupy the parent space
    }

    @Override
    public void setSignature(String signature) {
        String oldSignature = member.getSignature();
        if (!signature.equals(oldSignature)) {
            if (member.getDeclaringComponent().signatureExists(signature)) {
                JOptionPane.showMessageDialog(null, "Member \"" + signature + "\" already exists!");
            } else {
                try {
                    MemberParser.fillMethodComponents((Method) member, signature);
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Illegal format error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public String getSignature() {
        return member.getSignature();
    }

    private void setFontStyle(boolean isStatic, boolean isAbstract) {
        setStatic(isStatic);
        setAbstract(isAbstract);
    }
    
    private void setStatic(boolean isStatic) {
        Map<TextAttribute, Integer> fontAttributes = new HashMap<>();
        if (isStatic) {
            fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        } else {
            fontAttributes.put(TextAttribute.UNDERLINE, -1);
        }
        nameLabel.setFont(nameLabel.getFont().deriveFont(fontAttributes));
    }
    
    private void setAbstract(boolean isAbstract) {
        Map<TextAttribute, Float> fontAttributes = new HashMap<>();
        if (isAbstract) {
            fontAttributes.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);
        } else {
            fontAttributes.put(TextAttribute.POSTURE, -1f);
        }
        nameLabel.setFont(nameLabel.getFont().deriveFont(fontAttributes));
    }
    

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propName = evt.getPropertyName();
        if ("isStatic".equals(propName)) {
            updateIcon();
            setStatic((boolean) evt.getNewValue());
        } else if ("isAbstract".equals(propName)) {
            updateIcon();
            setAbstract((boolean) evt.getNewValue());
        } else if ("isFinal".equals(propName) || "isAbstract".equals(propName) || "isSynchronized".equals(propName) || "name".equals(propName) || "type".equals(propName) || "arguments".equals(propName)) {
            nameLabel.setLabel(((Method) member).getLabelText(getClassDiagramScene().isShowSimpleTypes()));
        } else if ("visibility".equals(evt.getPropertyName())) {
            updateIcon();
            updateVisibilityLabel();
        }
        notifyTopComponentModified();
        getScene().validate();
    }
}
