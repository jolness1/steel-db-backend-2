package com.example.exceptions

import java.util.UUID

sealed class SteelException(message: String) : Exception(message) {
    class NotFound(id: UUID) : SteelException("Steel with id number $id is not found")

}