package com.example.loadinganimation;

import javafx.animation.FadeTransition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class LoadingNode extends HBox {

    private final ObjectProperty<Duration> durationProperty = new SimpleObjectProperty<>(Duration.millis(600));

    public LoadingNode() {
        setAlignment(Pos.CENTER);
        for (int i = 0; i < 6; i++) {
            var circle = new Circle(10, Color.web("#3C3F41"));
            circle.setOpacity(0);
            getChildren().add(circle);
        }
        setSpacing(3);
    }

    public void play() {
        var t = new Thread(() -> {
            for (int i = 0; i < getChildren().size(); i++) {
                var animation = new FadeTransition(durationProperty.get(), getChildren().get(i));
                animation.setFromValue(0);
                animation.setToValue(1);
                animation.setOnFinished(actionEvent -> {
                    animation.playFromStart();
                });
                animation.setAutoReverse(true);
                animation.setCycleCount(2);
                animation.play();
                try {
                    Thread.sleep((long) (durationProperty.get().toMillis()/(getChildren().size() - 1)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    public void setDuration(Duration duration) {
        durationProperty.set(duration);
    }

    public Duration getDuration() {
        return durationProperty.get();
    }

    public ObjectProperty<Duration> durationProperty() {
        return durationProperty;
    }
}
