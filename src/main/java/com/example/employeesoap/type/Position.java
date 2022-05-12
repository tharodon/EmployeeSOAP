package com.example.employeesoap.type;
//todo название пакета во множественном числе. + назови лучше type
// done
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Position {
    JUNIOR("Junior", 18L, 3L, 50_000L, 70_000L),
    MIDDLE("Middle", 21L, 10L, 90_000L, 210_000L),
    SENIOR("Senior", 28L, 15L, 210_000L, 450_000L),
    MANAGER("Manager", 21L, 15L, 70_000L, 150_000L),

    INDEFINITE("Illegal position",null,null,null,null);

    private final String position;
    private final Long minAge;
    private final Long countTasksMax;
    private final Long salaryMin;
    private final Long salaryMax;

    //todo мне попробуй избавиться от Exception чтоб выше где использовалось не было try-catch - такие конструкции нагружают код.
    // как вариант добавь еще один тип например INDEFINITE или еще как-нибудь и возвращай его и сделай проверку на него.
    // Тип если он так определился то отправь ошибку пользователю, что он прислал не известную позицию. И так получиться, что ты избавишься от лишнего Exception
    // done
    public static Position getDefine(String define){
        if (JUNIOR.getPosition().equals(define)) {
            return JUNIOR;
        } else if (MIDDLE.getPosition().equals(define)) {
            return MIDDLE;
        } else if (SENIOR.getPosition().equals(define)) {
            return SENIOR;
        } else if (MANAGER.getPosition().equals(define)) {
            return MANAGER;
        }
        return INDEFINITE;
    }
}
