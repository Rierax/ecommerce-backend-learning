package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryEnum {

    ELECTRONICS("electronics"),
    OTHERS("other");

    private final String value;
}
