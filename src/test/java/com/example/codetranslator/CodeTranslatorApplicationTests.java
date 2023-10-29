package com.example.codetranslator;

import com.example.codetranslator.domain.UserRequest;
import com.example.codetranslator.service.TranslateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodeTranslatorApplicationTests {

    @Autowired
    TranslateService translateService;

    @Test
    void translateServiceTest() {

        UserRequest userRequest = new UserRequest(

                "using System.Transactions;\n" +
                        "using restful_crud_dapper.Models;\n" +
                        "\n" +
                        "namespace restful_crud_dapper.Services{\n" +
                        "    public class LanguageService{\n" +
                        "        private readonly LanguageRepository _languageRepository;\n" +
                        "\n" +
                        "        public LanguageService(LanguageRepository languageRepository){\n" +
                        "            _languageRepository = languageRepository;\n" +
                        "        }\n" +
                        "\n" +
                        "        public async Task<List<Language>> SelectAll(){\n" +
                        "            return await _languageRepository.SelectAll();\n" +
                        "        }\n" +
                        "\n" +
                        "        public async Task<Language> SelectById(int id){\n" +
                        "            return await _languageRepository.SelectById(id);\n" +
                        "        }\n" +
                        "\n" +
                        "        public async Task<int> CreateLanguage(Language language){\n" +
                        "\n" +
                        "            using (var transaction = new TransactionScope(TransactionScopeAsyncFlowOption.Enabled)){\n" +
                        "                \n" +
                        "                try {\n" +
                        "                    int result = await _languageRepository.Insert(language);\n" +
                        "                    transaction.Complete();\n" +
                        "                    return result;\n" +
                        "                    }\n" +
                        "                catch(Exception e) {\n" +
                        "                    transaction.Dispose();\n" +
                        "                    throw;\n" +
                        "                }\n" +
                        "                \n" +
                        "            }\n" +
                        "\n" +
                        "        }\n" +
                        "\n" +
                        "        public async Task<int> UpdateLanguage(Language language){\n" +
                        "            return await _languageRepository.Update(language);\n" +
                        "        }\n" +
                        "\n" +
                        "        public async Task<int> DeleteLanguage(int id){\n" +
                        "            return await _languageRepository.DeleteById(id);\n" +
                        "        }\n" +
                        "\n" +
                        "    }\n" +
                        "}",
                "c#", "java");

        System.out.println(translateService.translate(userRequest));
    }

}
