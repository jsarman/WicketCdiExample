/*
 * Copyright 2013 jsarman.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket.example.cdi.numberguess;

/**
 *
 * @author jsarman
 */
public class GuessResult {

    public enum Result {

        CORRECT,
        TOO_HIGH,
        TOO_SMALL
    }
    int guess;
    Result result;

    public GuessResult(int guess, Result result) {
        this.guess = guess;
        this.result = result;
    }

    Result getResult() {
        return result;
    }
}
