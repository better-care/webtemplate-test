/* Copyright 2020-2021 Better Ltd (www.better.care)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package care.better.platform.web.template.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * @author Primoz Delopst
 */

@SuppressWarnings({"InterfaceNeverImplemented", "unused"})
public interface CompositionConverter {

    /**
     * Converts RAW composition json string to the FLAT composition json string.
     *
     * @param template        Template xml string
     * @param defaultLanguage Template default language
     * @param rawComposition  RAW composition json string
     * @return FLAT composition json string
     */
    String convertRawToFlat(String template, String defaultLanguage, String rawComposition) throws Exception;

    /**
     * Converts RAW composition json string to the STRUCTURED composition json string.
     *
     * @param template        Template xml string
     * @param defaultLanguage Template default language
     * @param rawComposition  RAW composition json string
     * @return STRUCTURED composition json string
     */
    String convertRawToStructured(String template, String defaultLanguage, String rawComposition) throws Exception;

    /**
     * Converts FLAT composition json string to the RAW composition json string.
     *
     * @param template        Template xml string
     * @param defaultTemplateLanguage Template default language
     * @param flatComposition FLAT composition json string
     * @param compositionBuilderContext Map containing default values that will be used when composition is built
     * @return RAW composition json string
     */
    String convertFlatToRaw(String template, String defaultTemplateLanguage, String flatComposition, Map<String, Object> compositionBuilderContext) throws Exception;

    /**
     * Converts FLAT composition json string to the STRUCTURED composition json string.
     *
     * @param template        Template xml string
     * @param defaultLanguage Template default language
     * @param flatComposition FLAT composition json string
     * @param compositionBuilderContext Map containing default values that will be used when composition is built
     * @return STRUCTURED composition json string
     */
    String convertFlatToStructured(String template, String defaultLanguage, String flatComposition, Map<String, Object> compositionBuilderContext) throws Exception;

    /**
     * Converts STRUCTURED composition json string to the RAW composition json string.
     *
     * @param template              Template xml string
     * @param defaultLanguage       Template default language
     * @param structuredComposition STRUCTURED composition json string
     * @param compositionBuilderContext Map containing default values that will be used when composition is built
     * @return RAW composition json string
     */
    String convertStructuredToRaw(String template, String defaultLanguage, String structuredComposition, Map<String, Object> compositionBuilderContext) throws Exception;

    /**
     * Converts STRUCTURED composition json string to the FLAT composition json string.
     *
     * @param template              Template xml string
     * @param defaultLanguage       Template default language
     * @param structuredComposition STRUCTURED composition json string
     * @param compositionBuilderContext Map containing default values that will be used when composition is built
     * @return FLAT composition json string
     */
    String convertStructuredToFlat(String template, String defaultLanguage, String structuredComposition, Map<String, Object> compositionBuilderContext) throws Exception;


    /**
     * Update a RAW composition json string with values of FLAT composition json string.
     *
     * @param template              Template xml string
     * @param defaultLanguage       Template default language
     * @param rawComposition        RAW composition json string
     * @param compositionBuilderContext Map containing default values that will be used when composition is built
     * @param deltaValues                 Flat composition Map containing the values to be added
     * @return RAW composition json string
     */
    String updateRawComposition(String template, String defaultLanguage, String rawComposition, Map<String, Object> compositionBuilderContext, Map<String, Object> deltaValues) throws Exception;

    /**
     * Converts RAW composition json string to the FLAT composition.
     *
     * @param template        Template xml string
     * @param defaultLanguage Template default language
     * @param rawComposition  RAW composition json string
     * @return FLAT composition
     */
    @SuppressWarnings("AnonymousInnerClassMayBeStatic")
    default Map<String, Object> convertRawToFlat(
            String template,
            String defaultLanguage,
            String rawComposition,
            ObjectMapper objectMapper) throws Exception {
        return objectMapper.readValue(convertRawToFlat(template, defaultLanguage, rawComposition), new TypeReference<Map<String, Object>>() {});
    }

    /**
     * Converts RAW composition json string to the STRUCTURED composition.
     *
     * @param template        Template xml string
     * @param defaultLanguage Template default language
     * @param rawComposition  RAW composition json string
     * @return STRUCTURED composition
     */
    default JsonNode convertRawToStructured(
            String template,
            String defaultLanguage,
            String rawComposition,
            ObjectMapper objectMapper) throws Exception {
        return objectMapper.readValue(convertRawToStructured(template, defaultLanguage, rawComposition), JsonNode.class);
    }


    /**
     * Converts FLAT composition json string to the RAW composition.
     *
     * @param template        Template xml string
     * @param defaultLanguage Template default language
     * @param flatComposition FLAT composition json string
     * @param compositionBuilderContext Map containing default values that will be used when composition is built
     * @return RAW composition
     */
    default JsonNode convertFlatToRaw(
            String template,
            String defaultLanguage,
            String flatComposition,
            Map<String, Object> compositionBuilderContext,
            ObjectMapper objectMapper) throws Exception {
        return objectMapper.readValue(convertFlatToRaw(template, defaultLanguage, flatComposition, compositionBuilderContext), JsonNode.class);

    }

    /**
     * Converts STRUCTURED composition json string to the FLAT composition.
     *
     * @param template              Template xml string
     * @param defaultLanguage       Template default language
     * @param structuredComposition STRUCTURED composition json string
     * @param compositionBuilderContext Map containing default values that will be used when composition is built
     * @return FLAT composition
     */
    @SuppressWarnings("AnonymousInnerClassMayBeStatic")
    default Map<String, Object> convertStructuredToFlat(
            String template,
            String defaultLanguage,
            String structuredComposition,
            Map<String, Object> compositionBuilderContext,
            ObjectMapper objectMapper) throws Exception {
        return objectMapper.readValue(convertStructuredToFlat(template, defaultLanguage, structuredComposition, compositionBuilderContext), new TypeReference<Map<String, Object>>() {});
    }

    /**
     * Converts FLAT composition json string to the STRUCTURED composition.
     *
     * @param template        Template xml string
     * @param defaultLanguage Template default language
     * @param flatComposition FLAT composition json string
     * @param compositionBuilderContext Map containing default values that will be used when composition is built
     * @return STRUCTURED composition
     */
    default JsonNode convertFlatToStructured(
            String template,
            String defaultLanguage,
            String flatComposition,
            Map<String, Object> compositionBuilderContext,
            ObjectMapper objectMapper) throws Exception {
        return objectMapper.readValue(convertFlatToStructured(template, defaultLanguage, flatComposition, compositionBuilderContext), JsonNode.class);
    }

    /**
     * Converts STRUCTURED composition json string to the RAW composition.
     *
     * @param template              Template xml string
     * @param defaultLanguage       Template default language
     * @param structuredComposition STRUCTURED composition json string
     * @param compositionBuilderContext Map containing default values that will be used when composition is built
     * @return RAW composition
     */
    default JsonNode convertStructuredToRaw(
            String template,
            String defaultLanguage,
            String structuredComposition,
            Map<String, Object> compositionBuilderContext,
            ObjectMapper objectMapper) throws Exception {
        return objectMapper.readValue(convertStructuredToRaw(template, defaultLanguage, structuredComposition, compositionBuilderContext), JsonNode.class);
    }

    /**
     * Update a RAW composition json string with values of FLAT composition json string.
     *
     * @param template              Template xml string
     * @param defaultLanguage       Template default language
     * @param rawComposition        RAW composition json string
     * @param compositionBuilderContext Map containing default values that will be used when composition is built
     * @param deltaValues                 Flat composition Map containing the values to be added
     * @return RAW composition
     */
    default JsonNode updateRawComposition(
            String template,
            String defaultLanguage,
            String rawComposition,
            Map<String, Object> compositionBuilderContext,
            Map<String, Object> deltaValues,
            ObjectMapper objectMapper) throws Exception {
        return objectMapper.readValue(updateRawComposition(template, defaultLanguage, rawComposition, compositionBuilderContext, deltaValues), JsonNode.class);
    }
}
