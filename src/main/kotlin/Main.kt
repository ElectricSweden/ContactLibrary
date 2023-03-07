package com.bezkoder.kotlin.writefile

import java.awt.ContainerOrderFocusTraversalPolicy
import java.io.BufferedReader
import java.io.File
import java.io.FileReader


fun main(args: Array<String>)
{
    val fileName = "contactsKotlin.txt"

    val file = File(fileName)

    file.createNewFile()

    println("Welcome to the Contact List")
    while (true)
    {
        println("1: Add Contact, 2: Remove Contact, 3: Edit Contact, 4: View Contacts, 5. Write to file, 6. Sort alphabetical, 7. Read from file")
        when (readln())
        {
            "1" -> {
                println("Name, LastName, Phone, Email")
                val name = readln()
                val lastname = readln()
                val phoneNumber = readln()
                val email = readln()
                addContact(name, lastname, phoneNumber, email)
            }
            "2" -> {
                println("Write the name of the person you want to remove.")
                val searchName = readln()
                removeContact(searchName)
            }
            "3" -> {
                println("Write the name of the person you want to edit.")
                val searchName = readln()
                editContact(searchName)
            }
            "4" -> {
                viewContacts()
            }
            "5" -> {
                writeToFile()
            }
            "6" -> {
                sortAlphabetical()
            }
            "7" -> {
                readFromFile()
            }
            else -> {
                println("Not what you were looking for...")
            }
        }
    }
}

private val contacts: MutableList<Contact> = mutableListOf<Contact>()

fun addContact(name: String, lastname: String, phoneNumber: String, email: String)
{
    val contact = Contact(name, lastname, phoneNumber, email)
    contacts.add(contact)
}

fun removeContact(searchName: String)
{
    val iterator = contacts.iterator()
    while (iterator.hasNext())
    {
        val next = iterator.next()
        if (next.name == searchName)
        {
            iterator.remove()
        }
    }
}

fun editContact(searchName: String)
{
    val iterator = contacts.iterator()

    while (iterator.hasNext())
    {
        val next = iterator.next()
        if (next.name == searchName)
        {
            println("What to edit? 1: name, 2: lastname, 3: phoneNumber, 4: email")

            when(readln())
            {
                "1" -> {
                    val name = readln()
                    next.name = name
                }
                "2" -> {
                    val lastname = readln()
                    next.lastname = lastname
                }

                "3" -> {
                    val phoneNumber = readln()
                    next.phoneNumber = phoneNumber
                }

                "4" -> {
                    val email = readln()
                    next.email = email
                }
            }
        }
    }
}

fun viewContacts()
{
    for (contact in contacts)
    {
        println("Name: ${contact.name}, Last Name: ${contact.lastname}, Phone Number: ${contact.phoneNumber}, Email: ${contact.email}")
    }
}

fun sortAlphabetical()
{

    println("How to sort? 1. Alphabetical from First name. 2. Alphabetical from Last Name")
    when (readln())
    {
        "1" -> {
            val sortedContactList = contacts.sortedBy { it.name }
            for (contact in sortedContactList)
            {

                println("Name: ${contact.name}, Last Name: ${contact.lastname},  Phone Number: ${contact.phoneNumber}, Email: ${contact.email}")
            }
        }
        "2" -> {
            val sortedContactList = contacts.sortedBy { it.lastname }
            for (contact in sortedContactList)
            {

                println("Last Name: ${contact.lastname}, Name: ${contact.name},  Phone Number: ${contact.phoneNumber}, Email: ${contact.email}")
            }
        }
    }
}
fun writeToFile()
{
    getReadFromFile()

    File("./contactsKotlin.txt").printWriter().use { out ->
        contacts.forEach {
            out.println("${it.name}, ${it.lastname}, ${it.phoneNumber}, ${it.email}")
        }
    }

    //alternative BufferedWriter & BufferedReader for better performance than fileWriter
}

fun readFromFile() //Fix this
{
    File("./contactsKotlin.txt").forEachLine {
        println(it)
    }
}

fun getReadFromFile()
{
    File("./contactsKotlin.txt").forEachLine {
        val contactVariables = it.split(",").toTypedArray()
        addContact(contactVariables[0], contactVariables[1], contactVariables[2], contactVariables[3])
    }
}
    //addContact()
    //gör en loop som addar alla readlines kontakter till contacts


//Fix JSON
//Fix different files for classes
//Fix så att man kan lägga till flera email addresser och mobilnummer
private class Contact(var name: String, var lastname: String, var phoneNumber: String, var email: String)









































