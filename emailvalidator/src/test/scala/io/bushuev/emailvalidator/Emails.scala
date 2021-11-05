package io.bushuev.emailvalidator

object Emails {

  val invalidEmails = List(
    "nolocalpart.com",
    "test@example.com test",
    "user  name@example.com",
    "user   name@example.com",
    "example.@example.co.uk",
    "example@example@example.co.uk",
    "(test_exampel@example.fr",
    "example(example)example@example.co.uk",
    ".example@localhost",
    "ex\\ample@localhost",
    "example@local\\host",
    "example@localhost.",
    "user name@example.com",
    "username@ example . com",
    "example@(fake.com",
    "example@(fake.com",
    "username@example,com",
    "usern,ame@example.com",
    "user[na]me@example.com",
    "\"\"\"@iana.org",
    "\"\\\"@iana.org",
    "\"test\"test@iana.org",
    "\"test\"\"test\"@iana.org",
    "\"test\".\"test\"@iana.org",
    "\"test\".test@iana.org",
    "\r\ntest@iana.org",
    "\r\n test@iana.org",
    "\r\n \r\ntest@iana.org",
    "\r\n \r\ntest@iana.org",
    "\r\n \r\n test@iana.org",
    "test@iana.org \r\n",
    "test@iana.org \r\n ",
    "test@iana.org \r\n \r\n",
    "test@iana.org \r\n\r\n",
    "test@iana.org  \r\n\r\n ",
    "test@iana/icann.org",
    "test@foo;bar.com",
    1.toChar.toString + "a@test.com",
    "comment)example@example.com",
    "comment(example))@example.com",
    "example@example)comment.com",
    "example@example(comment)).com",
    "example@[1.2.3.4",
    "example@[IPv6:1:2:3:4:5:6:7:8",
    "exam(ple@exam).ple",
    "example@(example))comment.com"
  )

  val validEmails = List(
    "example@example.com",
    "example@example.co.uk",
    "example_underscore@example.fr",
    "example@localhost",
    "exam'ple@example.com",
    String.format("exam\\%sple@example.com", " "),
    "example((example))@fakedfake.co.uk",
    "example@faked(fake).co.uk",
    "example+@example.com",
    "example@with-hyphen.example.com",
    "with-hyphen@example.com",
    "example@1leadingnum.example.com",
    "1leadingnum@example.com",
    "инфо@письмо.рф",
    "\"username\"@example.com",
    "\"user,name\"@example.com",
    "\"user name\"@example.com",
    "\"user@name\"@example.com",
    "\"\\a\"@iana.org",
    "\"test\\ test\"@iana.org",
    "\"\"@iana.org",
    "\"\"@[]" /* kind of an edge case, valid for RFC 5322 but address literal is not for 5321 */,
    String.format("\"\\%s\"@iana.org", "\"")
  )

  val validWiki = List(
    // handled by RFC 5322 based servers
    "simple@example.com",
    "very.common@example.com",
    "disposable.style.email.with+symbol@example.com",
    "other.email-with-hyphen@example.com",
    "fully-qualified-domain@example.com",
    "user.name+tag+sorting@example.com", // (may go to user.name@example.com inbox depending on mail server)
    "x@example.com",                     // (one-letter local-part)
    "example-indeed@strange-example.com",
    "test/test@test.com",                // (slashes are a printable character, and allowed)
    "admin@mailserver1",                 // (local domain name with no TLD, although ICANN highly discourages dotless email addresses[10])
    "example@s.example",                 // (see the List of Internet top-level domains)
    "\" \"@example.org",                 // (space between the quotes)
    "\"john..doe\"@example.org",         // (quoted double dot)
    "mailhost!username@example.org",     // (bangified host route used for uucp mailers)
    "user%example.com@example.org",      // (% escaped mail route to user@example.com via example.org)
    "user-@example.org",                 // (local part ending with non-alphanumeric character from the list of allowed printable characters)

    // The example addresses below would not be handled by RFC 5322 based servers, but are permitted by RFC 6530
    "Pelé@example.com",         // Latin alphabet with diacritics
    "δοκιμή@παράδειγμα.δοκιμή", // Greek alphabet
    "медведь@с-балалайкой.рф",  // Cyrillic characters
    "संपर्क@डाटामेल.भारत"       // Devanagari characters
  )

  val invalidEmailListWiki = List(
    "Abc.example.com",                            // (no @ character)
    "A@b@c@example.com",                          // (only one @ is allowed outside quotation marks)
    "a\"b(c)d,e:f;g<h>i[j\\k]l@example.com",      // (none of the special characters in this local-part are allowed outside quotation marks)
    "just\"not\"right@example.com",               // (quoted strings must be dot separated or the only element making up the local-part)
    "this is\"not\\allowed@example.com",          // (spaces, quotes, and backslashes may only exist when within quoted strings and preceded by a backslash)
    "this\\ still\\\"not\\\\allowed@example.com", // (even if escaped (preceded by a backslash), spaces, quotes, and backslashes must still be contained by quotes)
    "QA[icon]CHOCOLATE[icon]@test.com"            // (icon characters)
  )

  val withWarnings = List(
    "1234567890123456789012345678901234567890123456789012345678901234+x@example.com", // (local-part is longer than 64 characters),
    "test@[127.0.0.0]"
  )
}
