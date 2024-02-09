/*
 * AMRIT – Accessible Medical Records via Integrated Technology Integrated EHR
 * (Electronic Health Records) Solution
 *
 * Copyright (C) "Piramal Swasthya Management and Research Institute"
 *
 * This file is part of AMRIT.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see https://www.gnu.org/licenses/.
 * 
 * package com.iemr.mcts.configure;
 * 
 * import org.apache.poi.xssf.model.SharedStringsTable; import
 * org.apache.poi.xssf.usermodel.XSSFRichTextString;
 * 
 * 
 * 
 * class SheetHandler extends DefaultHandler { private SharedStringsTable sst;
 * private String lastContents; private boolean nextIsString;
 * 
 * public SheetHandler(SharedStringsTable sst) { this.sst = sst; }
 * 
 * public void startElement(String uri, String localName, String name,
 * Attributes attributes) throws SAXException { // c => cell
 * if(name.equals("c")) { // Print the cell reference
 * System.out.print(attributes.getValue("r") + " - "); // Figure out if the
 * value is an index in the SST String cellType = attributes.getValue("t");
 * if(cellType != null && cellType.equals("s")) { nextIsString = true; } else {
 * nextIsString = false; } } // Clear contents cache lastContents = ""; }
 * 
 * public void endElement(String uri, String localName, String name) throws
 * SAXException { // Process the last contents as required. // Do now, as
 * characters() may be called more than once if(nextIsString) { int idx =
 * Integer.parseInt(lastContents); lastContents = new
 * XSSFRichTextString(sst.getEntryAt(idx)).toString(); nextIsString = false; }
 * 
 * // v => contents of a cell // Output after we've seen the string contents
 * if(name.equals("v")) { System.out.println(lastContents); } }
 * 
 * public void characters(char[] ch, int start, int length) throws SAXException
 * { lastContents += new String(ch, start, length); }
 * 
 * public static void main(String[] args) {
 * 
 * byte a = 54; byte b = 67;
 * 
 * byte c = (byte) (a + b);
 * 
 * System.out.println("byte: "+c); }
 * 
 * }
 */