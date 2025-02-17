import difflib
import nltk


class SpeachRecognitionMeasure:
    def __init__(self, ref, hyp) -> None:
        self.ref = self._preprocess(ref)
        self.hyp = self._preprocess(hyp)

    def wer(self):
        return self._diff(self.ref.split(), self.hyp.split())

    def cer(self):
        return self._diff(self.ref, self.hyp)

    def _diff(self, ref, hyp):
        diff = difflib.SequenceMatcher(None, ref, hyp)
        replacements = []
        insertions = []
        deletions = []
        for tag, i1, i2, j1, j2 in diff.get_opcodes():
            if tag == 'replace':
                replacements.append((ref[i1:i2], hyp[j1:j2]))
            elif tag == 'insert':
                insertions.append(hyp[j1:j2])
            elif tag == 'delete':
                deletions.append(ref[i1:i2])
        return {'replacements': replacements, 'insertions': insertions, 'deletions': deletions}

    tokenizer = nltk.SpaceTokenizer()
    allowed_chars = ["а", "б", "в", "г", "ґ", "д", "е", "є", "ж", "з", "и", "і", "ї", "й", "к", "л",
                     "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ь", "ю", "я", "-", "’"]

    def _preprocess(self, text: str) -> str:
        cleaned_text = text.lower().replace("'", "’").strip().replace(
            "?", ".").replace("!", ".").replace(",", "")

        cleaned_text = cleaned_text.split(".")
        out_text = []
        for text in cleaned_text:
            text = text.strip()
            words = [i for i in self.tokenizer.tokenize(
                text) if not i.isdigit()]
            words = [self.clear_word(w) for w in words]
            words = [w for w in words if w != ""]
            if all([len(i) <= 1 for i in words]):
                continue
            if len(words) == 0:
                continue
            out_text.append(
                " ".join(words))
        cleaned_text = "\n".join(out_text)
        return cleaned_text

    def clear_word(self, word: str) -> str:
        if word.startswith("-"):
            word = word[1:]
        if all([i in self.allowed_chars for i in word]):
            return word
        return ""


ref = 'Привіт світ, а що означає привіт для великих мовних моделей ?'
hyp = 'Привіт світ, що означає пивіт для великих мовних моделей, знаєш ?'

measure = SpeachRecognitionMeasure(ref, hyp)
print(measure.wer())
print(measure.cer())
