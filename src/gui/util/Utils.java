package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static java.lang.String.format;
import static java.time.LocalDate.parse;
import static java.util.Locale.US;
import static java.util.Locale.setDefault;

public class Utils {

    private Utils() {
    }

    public static Stage currentStage(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    public static Integer tryParseToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Double tryParseToDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static <T> void formatTableColumnDate(TableColumn<T, Date> tableColumn, String format) {
        tableColumn.setCellFactory(column -> new TableCell<T, Date>() {
            private SimpleDateFormat sdf = new SimpleDateFormat(format);

            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(sdf.format(item));
                }
            }
        });
    }

    public static <T> void formatTableColumnDouble(TableColumn<T, Double> tableColumn, int decimalPlaces) {
        tableColumn.setCellFactory(column -> new TableCell<T, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setDefault(US);
                    setText(format("%." + decimalPlaces + "f", item));
                }
            }
        });
    }

    public static void formatDatePicker(DatePicker datePicker, String format) {
        datePicker.setConverter(new StringConverter<LocalDate>() {

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);

            {
                datePicker.setPromptText(format.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }

}
