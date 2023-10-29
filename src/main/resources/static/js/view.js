document.addEventListener("DOMContentLoaded", function(){

    Prism.plugins.autoloader.languages_path = 'https://cdnjs.cloudflare.com/ajax/libs/prism/1.23.0/components/';

    Prism.plugins.toolbar.registerButton('copy-to-clipboard', function (env) {

        const button = document.createElement('button');
        button.className = 'copy-button';
        button.textContent = 'Copy';
        button.addEventListener('click', function () {
            const textArea = document.createElement('textarea');
            textArea.value = document.getElementById('translatedCode').textContent;
            document.body.appendChild(textArea);
            textArea.select();
            document.execCommand('copy');
            document.body.removeChild(textArea);
        });
        return button;
    });

})

document.getElementById('translateButton').addEventListener('click', function () {
    const code = document.getElementById('code').value;
    const language = document.getElementById('language').value;
    const targetLanguage = document.getElementById('targetLanguage').value;

    fetch('/translate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            requestCode: code,
            language: language,
            targetLanguage: targetLanguage
        }),
    })
        .then(response => response.json())
        .then(data => {
            const translatedCode = data.choices[0].message.content;
            const translatedCodeBlock = document.getElementById('translatedCode');
            const pre = document.getElementById('translationResult');

            pre.className = 'bg-dark p-3';

            // 사용자가 선택한 언어에 따라 클래스 추가
            translatedCodeBlock.className = 'language-'+ targetLanguage;

            // 코드 삽입
            translatedCodeBlock.textContent = translatedCode.replace(/'''/g, '');
            translatedCodeBlock.textContent = translatedCode.replace(/```/g, '');

            Prism.highlightElement(translatedCodeBlock); // 코드 강조 표시
        })
        .catch(error => {
            console.error('Translation error: ' + error);
        });
});