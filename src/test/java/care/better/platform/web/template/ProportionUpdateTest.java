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

package care.better.platform.web.template;

import care.better.platform.web.template.extension.WebTemplateTestExtension;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Marko Narat
 */
@ExtendWith(WebTemplateTestExtension.class)
public class ProportionUpdateTest extends AbstractWebTemplateTest {

    private ObjectMapper objectMapper;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JodaModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    public void testInvalidAttribute() throws Exception {
        String template = getFileContent("/res/Demo Vitals.opt");
        JsonNode structuredComposition = objectMapper.readTree(getFileContent("/res/vitals_proportion.json"));
        JsonNode composition = getCompositionConverter().convertStructuredToRaw(
                template,
                "en",
                structuredComposition.toString(),
                Collections.emptyMap(),
                objectMapper);
        assertThat(getCompositionValidator().validate(template, composition.toString())).isEmpty();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testInvalidAttributeFailed() throws Exception {
        String template = getFileContent("/res/Demo Vitals.opt");
        JsonNode structuredComposition = objectMapper.readTree(getFileContent("/res/vitals_proportion-fail.json"));
        JsonNode composition = getCompositionConverter().convertStructuredToRaw(
                template,
                "en",
                structuredComposition.toString(),
                Collections.emptyMap(),
                objectMapper);
        assertThat(getCompositionValidator().validate(template, composition.toString())).isNotEmpty();
    }
}
