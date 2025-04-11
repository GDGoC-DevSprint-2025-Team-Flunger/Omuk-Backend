//package devsprint.omuk.ingredient.service;
//
//import devsprint.omuk.S3Config;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import software.amazon.awssdk.core.sync.RequestBody;
//import software.amazon.awssdk.services.s3.S3Client;
//import software.amazon.awssdk.services.s3.model.PutObjectRequest;
//
//import java.io.IOException;
//import java.nio.ByteBuffer;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class S3Service {
//
//    private final S3Client s3Client;
//    private final S3Config s3Config;
//
//    public String uploadFile(MultipartFile file) throws IOException {
//        String bucketName = s3Config.getBucketName();
//        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
//
//        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
//                .bucket(bucketName)
//                .key(fileName)
//                .contentType(file.getContentType())
//                .build();
//
//        s3Client.putObject(putObjectRequest, RequestBody.fromByteBuffer(ByteBuffer.wrap(file.getBytes())));
//
//        return "https://" + bucketName + ".s3.amazonaws.com/" + fileName;
//    }
//}
