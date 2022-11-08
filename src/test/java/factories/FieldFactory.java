package factories;

import org.openqa.selenium.support.FindBy;
import service.AbstractSeleniumPage;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FieldFactory {


    public static Function<Class<? extends AbstractSeleniumPage>, List<Field>> getPageFields = page -> {
        Predicate<Field> filedHasAnnotation = field -> field.isAnnotationPresent(FindBy.class);
        Arrays.stream(page.getDeclaredFields()).filter(filedHasAnnotation).map(Field::getName).forEach(System.out::println);
        return Arrays.stream(page.getDeclaredFields()).filter(filedHasAnnotation).collect(Collectors.toList());
    };



}
