import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import kotlin.Pair
@Suppress(
    "DSL_SCOPE_VIOLATION",
    "MISSING_DEPENDENCY_CLASS",
    "UNRESOLVED_REFERENCE_WRONG_RECEIVER",
    "FUNCTION_CALL_EXPECTED"
)
plugins {
	alias(libs.plugins.quilt.loom)
    kotlin("jvm") version "1.7.0"
}

version = project.version
group = ""

repositories {
	maven { setUrl("https://maven.proxyfox.dev") }
}

dependencies {
	minecraft(libs.minecraft)
	mappings(loom.layered {
		addLayer(quiltMappings.mappings("org.quiltmc:quilt-mappings:${libs.versions.quilt.mappings.get()}:v2"))
	})
	modImplementation(libs.quilt.loader)

	modImplementation(libs.quilted.fabric.api)
	modImplementation(libs.sculk.api)
    modImplementation("net.fabricmc:fabric-language-kotlin:1.8.0+kotlin.1.7.0")
	include(libs.sculk.api)
}

java {
	// Still required by IDEs such as Eclipse and Visual Studio Code
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17

	// Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task if it is present.
	// If you remove this line, sources will not be generated.
	withSourcesJar()

	// If this mod is going to be a library, then it should also generate Javadocs in order to aid with developement.
	// Uncomment this line to generate them.
	// withJavadocJar()
}

// If you plan to use a different file for the license, don't forget to change the file name here!
tasks {
    jar {
        from("LICENSE") {
            rename { "${it}_${"archivesBaseName"}" }
        }
    }
    withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release.set(17)
    }
    withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
    processResources {
        inputs.property("version", version)

        filesMatching("quilt.mod.json") {
            expand(Pair("version", version))
        }
    }
}
