#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")
package ${PACKAGE_NAME};
#end
#if (${PACKAGE_NAME} == "entity")
    @Entity
#end


#parse("File Header.java")

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.*;
import javax.validation.constraints.NotNull;

// Lombok
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = { "" , "" })
@ToString(callSuper = true, of = { "" })
public class ${NAME} {
}
