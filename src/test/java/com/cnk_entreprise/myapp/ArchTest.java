package com.cnk_entreprise.myapp;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.cnk_entreprise.myapp");

        noClasses()
            .that()
            .resideInAnyPackage("com.cnk_entreprise.myapp.service..")
            .or()
            .resideInAnyPackage("com.cnk_entreprise.myapp.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.cnk_entreprise.myapp.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
