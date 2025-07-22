package com.aichatbot.controller;

import org.fxmisc.richtext.InlineCssTextArea;

public class FormatController {

    public void formatAndDisplayAIResponse(InlineCssTextArea c2w_ai_area, String c2w_ai_response) {
        c2w_ai_area.clear();

        for (String c2w_ai_line : c2w_ai_response.split("\n")) {
            c2w_ai_line = c2w_ai_line.trim();

            if (c2w_ai_line.isEmpty()) {
                c2w_ai_area.appendText("\n");
                continue;
            }

            int start, end;

            // Heading (ends with ':' and no asterisks)
            if (c2w_ai_line.endsWith(":") && !c2w_ai_line.contains("*")) {
                start = c2w_ai_area.getLength();
                c2w_ai_area.appendText(c2w_ai_line + "\n");
                end = c2w_ai_area.getLength();

                c2w_ai_area.setStyle(start, end, "-fx-font-weight: bold; -fx-font-size: 14px; -fx-fill: white;");
                continue;
            }

            // Bullet point (* ...)
            if (c2w_ai_line.startsWith("*")) {
                String clean = c2w_ai_line.replaceFirst("\\*+", "").trim();
                String formatted = "• " + clean + "\n";
                start = c2w_ai_area.getLength();
                c2w_ai_area.appendText(formatted);
                end = c2w_ai_area.getLength();

                c2w_ai_area.setStyle(start, end, "-fx-fill: white; -fx-font-size: 14px;");
                continue;
            }

            // Normal line
            start = c2w_ai_area.getLength();
            c2w_ai_area.appendText(c2w_ai_line + "\n");
            end = c2w_ai_area.getLength();

            c2w_ai_area.setStyle(start, end, "-fx-fill: white; -fx-font-size: 14px;");
        }
    }
}

