package ru.job4j.template;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class PhraseGeneratorTest {
    @Test
    void whenGeneratePhraseThenGetResult() {
        Generator generator = new PhraseGenerator();
        String template = "I am a ${name}, Who are ${subject}";
        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("name", "Roman");
        keyMap.put("subject", "you");
        String result = generator.produce(template, keyMap);
        assertThat(result).isEqualTo("I am a Roman, Who are you");
    }

    @Test
    void whenNotKeyThenGetException() {
        Generator generator = new PhraseGenerator();
        String template = "I am a ${name}, Who are ${subject}";
        String templateKey = "subject";
        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("name", "Roman");
        keyMap.put("surname", "Ivanov");
        generator.produce(template, keyMap);
        assertThat(keyMap).containsKeys(templateKey).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenManyKeysThenGetException() {
        Generator generator = new PhraseGenerator();
        String template = "I am a ${name}, Who are ${subject}";
        String key1 = "name";
        String key2 = "subject";
        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("name", "Roman");
        keyMap.put("subject", "you");
        keyMap.put("surname", "Ivanov");
        generator.produce(template, keyMap);
        assertThat(keyMap).containsOnlyKeys(key1, key2).isInstanceOf(IllegalArgumentException.class);
    }
}