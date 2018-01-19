/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity.email.imports;

import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Orjinali : https://github.com/Driftt/EmailReplyParser
 * 
 * INFO: Kullanıp kullanmayacağımdan pek emin değilim.
 * 
 * Paket olarak kullanmak için bir tane daha var : https://github.com/edlio/EmailReplyParser
 * 
 * @author Hakan Uygun
 */
public class EMailReplyParser {

    private final static CharMatcher NEW_LINE = CharMatcher.anyOf("\n");
    private final static CharMatcher CRLF = CharMatcher.anyOf("\r\n");

    private final static Pattern SIG_REGEX = Pattern.compile("(\u2014|--|__|-\\w)|(^Sent from my (\\w+\\s*){1,3})");
    private final static Pattern QUOTE_HDR_REGEX = Pattern.compile("^:etorw.*nO");
    private final static Pattern MULTI_QUOTE_HDR_REGEX = Pattern.compile("(?!On.*On\\s.+?wrote:)(On\\s(.+?)wrote:)");
    private final static Pattern QUOTED_REGEX = Pattern.compile("(>+)");
    
    private final String text;
    
    private List<Fragment> fragments = Lists.newArrayList();
    private boolean foundVisible = false;
    private Fragment fragment;

    public EMailReplyParser(String text) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(text));
        this.text = CRLF.replaceFrom(text, "\n");
    }

    public static EMailReplyParser read(String content) {
        EMailReplyParser parser = new EMailReplyParser(content);
        parser.read();
        return parser;
    }

    public static String parse(String content) {
        EMailReplyParser parser = read(content);
        return parser.getReply();
    }

    public List<Fragment> getFragments() {
        return fragments;
    }

    public String getReply() {
        return fragments.stream()
                //.filter(f -> !(f.isHidden() || f.isQuoted()))
                .filter(f -> !(f.isHidden()))
                .map(f -> f.getContent())
                .collect(Collectors.joining("\n"));
    }

    public void read() {
        String workingText = text;
        Matcher multiQuote = Pattern.compile(MULTI_QUOTE_HDR_REGEX.pattern(), Pattern.MULTILINE | Pattern.DOTALL)
                .matcher(workingText);

        if (multiQuote.find()) {
            String newQuoteHeader = NEW_LINE.replaceFrom(multiQuote.group(), "");
            workingText = Pattern.compile(MULTI_QUOTE_HDR_REGEX.pattern(), Pattern.DOTALL).matcher(workingText).replaceAll(
                    newQuoteHeader);
        }

        Lists.reverse(Splitter.on('\n').splitToList(workingText))
                .stream()
                .forEach(l -> scanLine(l));

        finishFragment();

        fragments = ImmutableList.copyOf(Lists.reverse(fragments));
    }

    private void scanLine(String line) {
        line = NEW_LINE.trimFrom(line);
        if (SIG_REGEX.matcher(line).lookingAt()) {
            line = NEW_LINE.trimLeadingFrom(line);
        }
        boolean isQuoted = QUOTED_REGEX.matcher(line).lookingAt();

        if (fragment != null && isStringEmpty(line)) {
            if (SIG_REGEX.matcher(fragment.lines.get(fragment.lines.size() - 1)).lookingAt()) {
                fragment.setSignature(true);
                finishFragment();
            }
        }

        if (fragment != null
                && ((fragment.isQuoted() == isQuoted)
                || (fragment.isQuoted() && (quoteHeader(line) || isStringEmpty(line))))) {
            fragment.lines.add(line);
        } else {
            finishFragment();
            fragment = new Fragment(isQuoted, line);
        }

    }

    private boolean quoteHeader(String line) {
        String reversed = new StringBuffer(line).reverse().toString();
        return QUOTE_HDR_REGEX.matcher(reversed).lookingAt();
    }

    private void finishFragment() {
        if (fragment != null) {
            fragment.finish();
            if (!foundVisible) {
                if (fragment.isQuoted()
                        || fragment.isSignature()
                        || isStringEmpty(fragment.getContent())) {
                    fragment.setHidden(true);
                } else {
                    foundVisible = true;
                }
            }
            fragments.add(fragment);
        }

        fragment = null;
    }

    private boolean isStringEmpty(String content) {
        return CharMatcher.WHITESPACE.trimFrom(content).isEmpty();
    }

    public static class Fragment {

        private boolean signature;
        private boolean hidden;
        private boolean quoted;
        private String content;
        private List<String> lines = Lists.newArrayList();

        public Fragment(boolean quoted, String firstLine) {
            this.quoted = quoted;
            this.lines.add(firstLine);
        }

        public String getContent() {
            return content;
        }

        public boolean isSignature() {
            return signature;
        }

        public void setSignature(boolean signature) {
            this.signature = signature;
        }

        public boolean isHidden() {
            return hidden;
        }

        public void setHidden(boolean hidden) {
            this.hidden = hidden;
        }

        public boolean isQuoted() {
            return quoted;
        }

        public void finish() {
            //content = CharMatcher.WHITESPACE.trimFrom(Lists.reverse(lines).stream().collect(Collectors.joining("\n")));
            content = Lists.reverse(lines).stream().collect(Collectors.joining(""));
            lines = ImmutableList.of();
        }
    }

}
